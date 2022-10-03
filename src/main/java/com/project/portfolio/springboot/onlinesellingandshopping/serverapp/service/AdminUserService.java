package com.project.portfolio.springboot.onlinesellingandshopping.serverapp.service;

import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.dto.UserDto;
import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.Entity.User;

import java.util.List;
import java.util.Optional;


public interface AdminUserService {

    Optional<User> addUser(UserDto userDto);
    Optional<User> editUser(UserDto userDto, String id);
    Optional<User> getUser(String name);
    List<User> getUsers();
    Iterable<User> getUserByYear();
    Iterable<User> getUserByMonth();
    Iterable<User> getUserByWeek();
    Iterable<User> getUserByDay();

}
