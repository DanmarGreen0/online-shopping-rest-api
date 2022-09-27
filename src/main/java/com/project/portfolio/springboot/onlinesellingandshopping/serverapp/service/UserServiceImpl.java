package com.project.portfolio.springboot.onlinesellingandshopping.serverapp.service;

import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.module.User;
import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.repository.UserRepository;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public Optional<User> getUser(String name){
        return this.userRepository.findByUsername(name);
    }

    @Override
    public List<User> getUsers(){
        return  this.userRepository.findAll();
    }

    @Override
    public Iterable<User> getUserByYear() {

        return null;
    }

    @Override
    public Iterable<User> getUserByMonth() {
        return null;
    }

    @Override
    public Iterable<User> getUserByWeek() {
        return null;
    }

    @Override
    public Iterable<User> getUserByDay() {
        return null;
    }


}
