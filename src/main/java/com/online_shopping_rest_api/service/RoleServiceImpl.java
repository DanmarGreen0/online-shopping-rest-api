package com.online_shopping_rest_api.service;

import com.online_shopping_rest_api.entity.Role;
import com.online_shopping_rest_api.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> getRole(int id){

        Optional<Role> role = this.roleRepository.findById(id);

        //To Do:
        //throw exception

        return role;
    }

    @Override
    public List<Role> getRoles() {
        return this.roleRepository.findAll();
    }
}
