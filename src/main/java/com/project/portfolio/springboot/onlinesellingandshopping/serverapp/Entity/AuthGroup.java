package com.project.portfolio.springboot.onlinesellingandshopping.serverapp.Entity;

import javax.persistence.*;

@Entity
@Table(name="Auth_User_Group")
public class AuthGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Auth_User_Group_Id")
    private Integer id;

    @Column(name="Username")
    private String username;

    @Column(name="Auth_Group")
    private String authGroup;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthGroup() {
        return authGroup;
    }

    public void setAuthGroup(String authGroup) {
        this.authGroup = authGroup;
    }
}
