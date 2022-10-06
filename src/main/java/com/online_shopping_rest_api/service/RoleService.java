package com.online_shopping_rest_api.service;

import com.online_shopping_rest_api.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> getRole(int id);
    List<Role> getRoles();
}
