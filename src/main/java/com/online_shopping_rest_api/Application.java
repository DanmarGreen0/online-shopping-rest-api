package com.online_shopping_rest_api;

import com.online_shopping_rest_api.entity.Role;
import com.online_shopping_rest_api.entity.User;
import com.online_shopping_rest_api.repository.RoleRepository;
import com.online_shopping_rest_api.repository.UserRepository;
import com.online_shopping_rest_api.security.PasswordEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.*;


@SpringBootApplication
public class Application implements CommandLineRunner {

	public boolean alreadySetup = false;

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	private static Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		LOG.info("STARTING THE APPLICATION");
		SpringApplication.run(Application.class, args);
		LOG.info("APPLICATION FINISHED");
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		if(alreadySetup)
			return;

		final Optional<Role> masterAdminRole = createRoleIfNotFound("ROLE_MASTER_ADMIN");
		final Optional<Role> adminRole = createRoleIfNotFound("ROLE_ADMIN");
		final Optional<Role> userRole = createRoleIfNotFound("ROLE_USER");

		long millis=System.currentTimeMillis();
		Date date = new Date(millis);

		Set<Role> roles = new HashSet<>();
		roles.add(masterAdminRole.get());
		roles.add(adminRole.get());
		roles.add(userRole.get());

			User user = new User();
			user.setUsername("danmargreen");
			user.setFirstName("Danmar");
			user.setLastName("Green");
			user.setDateOfBirth("1996-17-01");
			user.setAddress("5955 Skyline Dr, West Linn, OR, 97068");
			user.setEmail("danmargreen0@gmail.com");

			PasswordEncoder passwordEncoder = new PasswordEncoder("Terren17");
			user.setPassword(passwordEncoder.getEncodedPassword());
			user.setPhoneNo("(503)-833-2453");
			user.setCreatedAt(date);
			user.setModifiedAt(date);

			user.setRoles(roles);

			userRepository.saveAndFlush(user);


		alreadySetup = true;
	}

	@Transactional
	Optional<Role> createRoleIfNotFound(String role){

		Optional<Role> roles = roleRepository.findByRole(role);

		if(!roles.isPresent()){
			roles = Optional.of( new Role());
			roles.get().setRole(role);
			roleRepository.saveAndFlush(roles.get());
		};


		return roles;
	}
}
