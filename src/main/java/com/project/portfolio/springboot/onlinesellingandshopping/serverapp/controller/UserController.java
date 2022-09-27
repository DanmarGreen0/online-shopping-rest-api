package com.project.portfolio.springboot.onlinesellingandshopping.serverapp.controller;

import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.module.User;
import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping(path="/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody Object getUsers(){

       List users = (List<User>) this.userService.getUsers();

       if(users.size() > 0)
           return users;

        return "There aren't any user.";
    }

    @GetMapping(path="/user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody Object getUser(String name){

       Optional user = this.userService.getUser(name);

       return user.orElse("The user with the username " + name + " doesn't exist.");
    }



}
