package com.online_shopping_rest_api.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    final private String password;

    public PasswordEncoder(String password) {
        this.password = password;
    }

    public BCryptPasswordEncoder bcryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

   public String getEncodedPassword(){
        return bcryptPasswordEncoder().encode(password);
   }

}
