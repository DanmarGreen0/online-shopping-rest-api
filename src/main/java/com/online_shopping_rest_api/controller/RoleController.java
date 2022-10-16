package com.online_shopping_rest_api.controller;

import com.online_shopping_rest_api.entity.Role;
import com.online_shopping_rest_api.service.RoleServiceImpl;
import com.online_shopping_rest_api.util.MapRoleEntityToJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class RoleController {
    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping(path="/roles", produces = "application/json")
    @PreAuthorize("hasRole('ROLE_MASTER_ADMIN')")
    public ResponseEntity<List<Map<String,Object>>> getRoles(){

        List<Role> roles = roleService.getRoles();
        List<Map<String,Object>> json = new MapRoleEntityToJson().getRolesJson(roles);

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping(path="/role/{id}", produces = "application/json")
    @PreAuthorize("hasRole('ROLE_MASTER_ADMIN')")
    public ResponseEntity<Object> getRole(@PathVariable int id){

        if(id < 0)
            return new ResponseEntity<>("The param id value must be equal or greater than zero.", HttpStatus.BAD_REQUEST);

        Optional<Role> role = roleService.getRole(id);
        Map<String,Object> json = new MapRoleEntityToJson().getRoleJson(role.get());

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

}
