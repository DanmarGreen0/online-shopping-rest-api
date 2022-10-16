package com.online_shopping_rest_api.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Roles")
@Data
public class Role {
    //To do: 1) doc exceptions
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Role")
    private String role;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    List<User> users;

}

