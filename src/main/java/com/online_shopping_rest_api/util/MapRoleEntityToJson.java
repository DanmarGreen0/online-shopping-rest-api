package com.online_shopping_rest_api.util;

import com.online_shopping_rest_api.entity.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Component
public class MapRoleEntityToJson {
    public List<Map<String,Object>> getRolesJson(List<Role> roles){

        List<Map<String,Object>> userJsonList = new ArrayList<>();

        roles.forEach(role -> {

            LinkedHashMap<String,Object> json1 = new LinkedHashMap<>();
            List<Map<String,String>> roleJsonList = new ArrayList<>();

            json1.put("id" , role.getId().toString());
            json1.put("name" , role.getRole());

            role.getUsers().forEach(user -> {
                LinkedHashMap<String,String> json2 = new LinkedHashMap<>();
                json2.put("id" , user.getId().toString());
                json2.put("username" , user.getUsername());
                json2.put("first name" , user.getFirstName());
                json2.put("last name", user.getLastName());
                json2.put("date of birth" , user.getDateOfBirth());
                json2.put("email" , user.getEmail());
                json2.put("address" , user.getAddress());
                json2.put("phone no" , user.getPhoneNo());
                json2.put("create at" , user.getCreatedAt().toString());
                json2.put("modified at" , user.getModifiedAt().toString());
                roleJsonList.add(json2);
            });

            json1.put("users" , roleJsonList);

            userJsonList.add(json1);
       });

        return userJsonList;
    }

    public Map<String,Object> getRoleJson(Role role){

        LinkedHashMap<String,Object> json = new LinkedHashMap<>();
        LinkedHashMap<String,String> json2 = new LinkedHashMap<>();

        json.put("id" , role.getId().toString());
        json.put("role" , role.getRole());

        role.getUsers().forEach(user -> {
            json2.put("id" , user.getId().toString());
            json2.put("username" , user.getUsername());
            json2.put("first name" , user.getFirstName());
            json2.put("last name", user.getLastName());
            json2.put("date of birth" , user.getDateOfBirth());
            json2.put("email" , user.getEmail());
            json2.put("address" , user.getAddress());
            json2.put("phone no" , user.getPhoneNo());
            json2.put("create at" , user.getCreatedAt().toString());
            json2.put("modified at" , user.getModifiedAt().toString());
        });

        json.put("users" , json2);

        return json;
    }

}
