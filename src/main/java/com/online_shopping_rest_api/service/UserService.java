package com.online_shopping_rest_api.service;

import com.online_shopping_rest_api.dto.UserDTO;
import com.online_shopping_rest_api.entity.User;
import org.springframework.security.core.Authentication;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface UserService {
    User createAdminUser(UserDTO userDTO) throws Exception;
    String updateUser(Authentication authentication, Map<String,Object> accountChanges, int id);
    String deleteUser(Authentication authentication, int id);
    Optional<User> getUser(int id) throws AccountNotFoundException;
    List<User> getUsers();

    //To do:
    Iterable<User> getUserByYear();
    Iterable<User> getUserByMonth();
    Iterable<User> getUserByWeek();
    Iterable<User> getUserByDay();

}
