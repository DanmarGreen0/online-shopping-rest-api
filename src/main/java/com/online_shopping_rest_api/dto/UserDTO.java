package com.online_shopping_rest_api.dto;

import com.online_shopping_rest_api.entity.Role;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
public class UserDTO {
    private final Integer userId;
    private final String username, firstName, lastName, password, address, dateOfBirth, email, phoneNo;
    private List<Role> roles;

    public static class Builder{

        private Integer userId;
        private String username, firstName, lastName, password, address, dateOfBirth, email, phoneNo;
        private List<Role> roles;

        //To do: 1) handle exception in each function because these param a optional 2) doc exception
        public Builder userId(Integer userId) {
            this.userId = userId; return this;
        }

        public Builder username(String username){
            this.username = username;   return this;
        }

        public Builder firstName(String firstName){
            this.firstName = firstName;   return this;
        }

        public Builder lastName(String lastName){
            this.lastName = lastName;   return this;
        }

        public Builder password(String password){
            this.password = password;   return this;
        }

        public Builder address(String address){
            this.address = address;   return this;
        }

        public Builder dateOfBirth(String dateOfBirth){
            this.dateOfBirth = dateOfBirth;   return this;
        }

        public Builder email(String email){
            this.email = email;   return this;
        }

        public Builder phoneNo(String phoneNo){
            this.phoneNo = phoneNo;   return this;
        }

        public Builder roles(List<Role> roles){
            this.roles = roles;   return this;
        }

    }

    private UserDTO(Builder builder) {
        this.userId = builder.userId;
        this.username = builder.username;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.password = builder.password;
        this.address = builder.address;
        this.dateOfBirth = builder.dateOfBirth;
        this.email = builder.email;
        this.phoneNo = builder.phoneNo;
        this.roles = builder.roles;
    }
}
