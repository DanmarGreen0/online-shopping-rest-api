package com.online_shopping_rest_api.service;

import com.online_shopping_rest_api.entity.Role;
import com.online_shopping_rest_api.entity.User;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;


public interface UserService {

    User addAdminUser(User user);
    Optional<User> getUser(int id) throws AccountNotFoundException;
    List<User> getUsers();

    Iterable<User> getUserByYear();
    Iterable<User> getUserByMonth();
    Iterable<User> getUserByWeek();
    Iterable<User> getUserByDay();

}
