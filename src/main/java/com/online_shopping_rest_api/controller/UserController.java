package com.online_shopping_rest_api.controller;

import com.online_shopping_rest_api.entity.User;
import com.online_shopping_rest_api.service.UserServiceImpl;
import com.online_shopping_rest_api.util.MapUserEntityToJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.*;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping(path="/users", produces = "application/json")
    @PreAuthorize("hasAnyRole('ROLE_MASTER_ADMIN','ROLE_ADMIN')")
    public ResponseEntity<List<LinkedHashMap<String,Object>>> getUsers() {

        List <User> users = new ArrayList<>(userService.getUsers());
        List<LinkedHashMap<String,Object>> listJson = new MapUserEntityToJson().getUsersJson(users);

       return new ResponseEntity<>(listJson, HttpStatus.OK);
    }
    @GetMapping(path="/user/{id}", produces = "application/json")
    @PreAuthorize("hasAnyRole('ROLE_MASTER_ADMIN','ROLE_ADMIN')")
    public ResponseEntity<Object> getUser(@PathVariable int id) throws AccountNotFoundException {

        if(id < 0)
            return new ResponseEntity<>("The param id value must be equal or greater than zero.", HttpStatus.BAD_REQUEST);

        Optional<User> user = userService.getUser(id);
        Map<String,Object> json = new MapUserEntityToJson().getUserJson(user.get());

       return new ResponseEntity<>(json, HttpStatus.OK);
    }
    @PostMapping(path = "/admin/user")
    @PreAuthorize("hasRole('ROLE_MASTER_ADMIN')")
    public ResponseEntity<Map<String,Object>> addAdminUser(@RequestBody User user){

        MapUserEntityToJson mapUserEntityToJson = new MapUserEntityToJson();
       Map<String,Object> userJson = mapUserEntityToJson.getUserJson(userService.addAdminUser(user));

        return new ResponseEntity<>(userJson, HttpStatus.CREATED);
    }

}
