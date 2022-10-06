package com.online_shopping_rest_api.util;

import com.online_shopping_rest_api.entity.User;
import org.springframework.stereotype.Component;

import java.util.*;

public class MapUserEntityToJson {
    public List<LinkedHashMap<String,Object>> getUsersJson(List<User> users){

        List<LinkedHashMap<String,Object>> userJsonList = new ArrayList<>();


        users.forEach(user -> {

            List<LinkedHashMap<String,String>> rolesJsonList = new ArrayList<>();

            user.getRoles().forEach(role -> {
                LinkedHashMap<String,String> json2 = new LinkedHashMap<>();
                json2.put("id" , role.getId().toString());
                json2.put("name" , role.getRole());
                rolesJsonList.add(json2);
            });

            LinkedHashMap<String,Object> json1 = new LinkedHashMap<>();
            json1.put("id" , user.getId().toString());
            json1.put("username" , user.getUsername());
            json1.put("first name" , user.getFirstName());
            json1.put("last name", user.getLastName());
            json1.put("date of birth" , user.getDateOfBirth());
            json1.put("email" , user.getEmail());
            json1.put("address" , user.getAddress());
            json1.put("phone no" , user.getPhoneNo());
            json1.put("create at" , user.getCreatedAt().toString());
            json1.put("modified at" , user.getModifiedAt().toString());
            json1.put("roles" , rolesJsonList);

            userJsonList.add(json1);

        });
        return userJsonList;
    }

    public Map<String,Object> getUserJson(User user){

        List<LinkedHashMap<String,String>> listJson = new ArrayList<>();

        user.getRoles().forEach(role -> {
            LinkedHashMap<String,String> json2 = new LinkedHashMap<>();

            json2.put("id" , role.getId().toString());
            json2.put("name" , role.getRole());

            listJson.add(json2);
        });

        LinkedHashMap<String,Object> json1 = new LinkedHashMap<>();
        json1.put("id" , user.getId().toString());
        json1.put("username" , user.getUsername());
        json1.put("first name" , user.getFirstName());
        json1.put("last name", user.getLastName());
        json1.put("date of birth" , user.getDateOfBirth());
        json1.put("email" , user.getEmail());
        json1.put("address" , user.getAddress());
        json1.put("phone no" , user.getPhoneNo());
        json1.put("create at" , user.getCreatedAt().toString());
        json1.put("modified at" , user.getModifiedAt().toString());
        json1.put("roles" , listJson);

        return json1;
    }

}
