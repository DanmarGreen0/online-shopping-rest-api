package com.online_shopping_rest_api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Users")
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id", nullable = false)
    private Integer id;
    @Column(name="Username", nullable = false, unique = true)
    private String username;
    @Column(name="First_Name", nullable = false)
    private String firstName;
    @Column(name="Last_Name", nullable = false)
    private String lastName;
    @Column(name="Password", nullable = false)
    private String password;
    @Column(name="Address", nullable = false)
    private String address;
    @Column(name="Date_Of_Birth", nullable = false)
    private String dateOfBirth;
    @Column(name="Email", nullable = false)
    private String email;
    @Column(name="Phone_No", nullable = false)
    private String phoneNo;
    @Column(name="Created_At", nullable = false)
    private Date createdAt;
    @Column(name="Modified_At", nullable = false)
    private Date modifiedAt;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Has_Roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public static class Builder{
        private Integer id  = 0;
        private final String username;
        private final String firstName;
        private final String lastName;
        private final String password;
        private final String address;
        private final String dateOfBirth;
        private final String email;
        private final String phoneNo;
        private final Date createdAt;
        private final Date modifiedAt;
        private final List<Role> roles;

        public Builder(String username, String firstName, String lastName, String password,
                       String address, String dateOfBirth, String email, String phoneNo,
                       Date createdAt, Date modifiedAt, List<Role> roles) throws Exception {

            //To do: 1) use regex to check parameter validity. 2) document exception
            if(firstName == null){
                throw new Exception("Enter last name.");
            }
            if(lastName == null){
                throw new Exception("Enter first name.");
            }
            if( email == null ){
                throw new Exception("Enter email address.");
            }
            if(password == null){
                throw new Exception("Enter email address.");
            }

            this.username = username;
            this.firstName = firstName;
            this.lastName = lastName;
            this.password = password;
            this.address = address;
            this.dateOfBirth = dateOfBirth;
            this.email = email;
            this.phoneNo = phoneNo;
            this.createdAt = createdAt;
            this.modifiedAt = modifiedAt;
            this.roles = roles;
        }

        public Builder Id(Integer id){
            this.id = id;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }

    private User(Builder builder) {
        id = builder.id;
        username = builder.username;
        firstName = builder.firstName;
        lastName = builder.lastName;
        password = builder.password;
        address = builder.address;
        dateOfBirth = builder.dateOfBirth;
        email = builder.email;
        phoneNo = builder.phoneNo;
        createdAt = builder.createdAt;
        modifiedAt = builder.modifiedAt;
        roles = builder.roles;
    }
}
