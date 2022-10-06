package com.online_shopping_rest_api.service;

import com.online_shopping_rest_api.entity.Role;
import com.online_shopping_rest_api.entity.User;
import com.online_shopping_rest_api.repository.RoleRepository;
import com.online_shopping_rest_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public User addAdminUser(User user) {

        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        user.setCreatedAt(date);
        user.setModifiedAt(date);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        Optional<Role> adminRole = roleRepository.findByRole("ROLE_ADMIN");
        Optional<Role> userRole = roleRepository.findByRole("ROLE_USER");
        Set<Role>  role = new HashSet<>();
        role.add(adminRole.get());
        role.add(userRole.get());
        user.setRoles(role);

        return userRepository.saveAndFlush(user);
    }
    @Override
    public Optional<User> getUser(int id) throws AccountNotFoundException {

        Optional<User> user = this.userRepository.findById(id);

        if(!user.isPresent())
            throw new AccountNotFoundException("Could not be found a user account associated with the id = " + id + ".");

        return user;
    }
    @Override
    public List<User> getUsers(){
        return this.userRepository.findAll();
    }
    @Override
    public Iterable<User> getUserByYear() {

        return null;
    }
    @Override
    public Iterable<User> getUserByMonth() {
        return null;
    }
    @Override
    public Iterable<User> getUserByWeek() {
        return null;
    }
    @Override
    public Iterable<User> getUserByDay() {
        return null;
    }


}
