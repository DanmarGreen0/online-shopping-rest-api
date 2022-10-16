package com.online_shopping_rest_api.util;

import com.online_shopping_rest_api.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OperationAuthorizationChecker {
    private final User user;
    private final Authentication authentication;
    private final Map<String,Object> accountChanges;

    public OperationAuthorizationChecker(){
        this.user = null;
        this.authentication = null;
        this.accountChanges = null;
    }
    public OperationAuthorizationChecker(User user, Authentication authentication) {
        this.user = user;
        this.authentication = authentication;
        this.accountChanges = null;
    }
    public OperationAuthorizationChecker(User user, Authentication authentication, Map<String, Object> accountChanges) {
        this.user = user;
        this.authentication = authentication;
        this.accountChanges = accountChanges;
    }



    public boolean check(){

       UserDetails userDetails = (UserDetails) authentication.getPrincipal(); //get the role of the user
       String loginUserUsername = userDetails.getUsername();
       String usernameOnRecord = user.getUsername();
       boolean pass = false;

        if(usernameOnRecord.equals(loginUserUsername)){
            pass = true;
        }

        return pass;
    }
}
