package com.online_shopping_rest_api;

import com.online_shopping_rest_api.entity.Role;
import com.online_shopping_rest_api.entity.User;
import com.online_shopping_rest_api.repository.RoleRepository;
import com.online_shopping_rest_api.repository.UserRepository;
import com.online_shopping_rest_api.security.PasswordEncoder;
import com.online_shopping_rest_api.util.DateGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootApplication
public class Application implements CommandLineRunner {
	private boolean alreadySetup = false;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

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

		PasswordEncoder passwordEncoder = new PasswordEncoder();
		DateGenerator dateGenerator = new DateGenerator();

		List<Role> roles = new ArrayList<>();
		roles.add(masterAdminRole.get());
		roles.add(adminRole.get());
		roles.add(userRole.get());

		User user = new User.Builder("danmargreen","Danmar","Green", passwordEncoder.getEncodedPassword("Terren17"),
				"5955 Skyline Dr, West Linn, OR, 97068","1996-17-01","danmargreen0@gmail.com",
				"(503)-833-2453", dateGenerator.getSQLDate(),dateGenerator.getSQLDate(),roles).build();

		userRepository.saveAndFlush(user);

		alreadySetup = true;
	}

	@Transactional
	private Optional<Role> createRoleIfNotFound(String role){

		Optional<Role> roles = roleRepository.findByRole(role);

		if(!roles.isPresent()){
			roles = Optional.of( new Role());
			roles.get().setRole(role);
			roleRepository.saveAndFlush(roles.get());
		};


		return roles;
	}
}
