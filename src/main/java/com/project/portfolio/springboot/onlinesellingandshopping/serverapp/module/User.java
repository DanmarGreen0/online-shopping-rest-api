package com.project.portfolio.springboot.onlinesellingandshopping.serverapp.module;



import javax.persistence.*;
import java.sql.Date;

@Entity
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
    private Date dateOfBirth;
    @Column(name="Email", nullable = false)
    private String email;
    @Column(name="Phone_No", nullable = false)
    private String phoneNo;
    @Column(name="Created_At", nullable = false)
    private String createdAt;
    @Column(name="Modified_At", nullable = false)
    private String modifiedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
