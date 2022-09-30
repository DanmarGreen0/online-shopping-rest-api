package com.project.portfolio.springboot.onlinesellingandshopping.serverapp.service;

import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.module.User;
import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.repository.UserRepository;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    //source: https://support.google.com/mail/answer/9211434?hl=en#:~:text=Usernames%20can%20contain%20letters%20(a%2Dz,in%20a%20row.
    static boolean usernameValidator(String username){

        boolean isValid = false;
        int usernameMinLen =  6;
        int usernameMaxLen =  30;
        Pattern illegalSymbols = Pattern.compile("[&=_'-+,<>]");
        Pattern illegalSymbols1 = Pattern.compile("[.]{2,}");

        if(username.length() >= usernameMinLen &&
                username.length() <= usernameMaxLen){ //check length
            if (Character.compare(username.charAt(0),'.') != 0 && !username.endsWith(".")) //check for . at the beginning and end
                if(!illegalSymbols.matcher(username).find() &&
                        !illegalSymbols1.matcher(username).find()) //check for other illegal symbols
                    isValid = true;
        }

        return isValid;
    }

    static boolean firstNameAndLastNameValidator(String name){

        boolean isValid = false;
        int minNameLen = 0;
        int maxNameLen = 24;

        if(name.length() > minNameLen && name.length() <= maxNameLen)
            isValid = true;

        return isValid;
    }

    static boolean passwordValidator(String password){

        boolean isValid = false;
        int validPasswordMinLen = 8;
        int validPasswordMaxLen = 24;

        if(password.length() >= validPasswordMinLen &&
                password.length() <= validPasswordMaxLen)
            isValid = true;

        return isValid;
    }


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
