package com.project.portfolio.springboot.onlinesellingandshopping.serverapp.service;

import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.module.User;
import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {

    Optional<User> getUser(String name);
    List<User> getUsers();
    Iterable<User> getUserByYear();
    Iterable<User> getUserByMonth();
    Iterable<User> getUserByWeek();
    Iterable<User> getUserByDay();

}
