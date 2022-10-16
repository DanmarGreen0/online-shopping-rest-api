package com.online_shopping_rest_api.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public BCryptPasswordEncoder bcryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    public String getEncodedPassword(String password){
        return bcryptPasswordEncoder().encode(password);
   }

}
