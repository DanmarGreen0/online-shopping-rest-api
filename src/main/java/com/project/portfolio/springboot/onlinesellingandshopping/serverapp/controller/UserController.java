package com.project.portfolio.springboot.onlinesellingandshopping.serverapp.controller;

import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.dto.UserDto;
import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.Entity.User;
import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.service.AdminUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private AdminUserServiceImpl userService;

    @GetMapping(path="/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody Object getUsers(){

        List users = (List<User>) this.userService.getUsers();

        if(users.size() > 0)
            return users;

        return "There aren't any user.";
    }

    @PostMapping(path="/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("permitAll()")
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto){

        if(userDto.getUsername() == null || userDto.getFirstName() == null || userDto.getLastName() == null  || userDto.getPassword() == null ||
                userDto.getAddress() == null  || userDto.getDateOfBirth() == null  || userDto.getEmail() == null  || userDto.getPhoneNo() == null ){

            return new ResponseEntity<>("Missing request body or a request body fields.", HttpStatus.BAD_REQUEST);
        }

        long millis = System.currentTimeMillis();
        Date date = new java.sql.Date(millis);

        userDto.setCreatedAt(date.toString());
        userDto.setModifiedAt(date.toString());

        userService.addUser(userDto);

        return new ResponseEntity<>("Account created successfully.", HttpStatus.CREATED);
    }

    @PatchMapping("/users/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> editUser(@RequestBody UserDto userDto, @PathVariable Integer id){

        if(userDto == null)
            return new ResponseEntity<>("Request body must have at least one field.",HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>("Account successfully edited.",HttpStatus.NO_CONTENT);
    }

    @GetMapping(path="/user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody Object getUser(String name){

       Optional user = this.userService.getUser(name);

       return user.orElse("The user with the username " + name + " doesn't exist.");
    }



}
