package com.project.portfolio.springboot.onlinesellingandshopping.serverapp.service;

import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.Entity.AuthGroup;
import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.dto.UserDto;
import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.exception.InvalidRegistrationFieldException;
import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.Entity.User;
import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.repository.AuthGroupRepository;
import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.repository.UserRepository;
import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.util.FieldsCheckedReport;
import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.util.UserRegistrationInputValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthGroupRepository authGroupRepository;

    @Override
    public Optional<User> addUser(UserDto userDto) {

        FieldsCheckedReport fieldsCheckedReport = registrationInputsChecker(userDto);

        if(fieldsCheckedReport.getStatus().compareTo("FAILED") == 0){
            throw new InvalidRegistrationFieldException(fieldsCheckedReport.getInvalidFieldsMessage());
        }

        User user = mapDtoToEntity(userDto);

        user.setPassword(passwordEncoder().encode(user.getPassword()));
        User user1 = userRepository.saveAndFlush(user);
        AuthGroup authGroup = new AuthGroup();

        authGroup.setId(user1.getId());
        authGroup.setUsername(user1.getUsername());
        authGroup.setAuthGroup("ROLE_ADMIN");

        this.authGroupRepository.save(authGroup);

        return Optional.of(user1);
    }

    @Override
    public Optional<User> editUser(UserDto userDto, String id) {
        return Optional.empty();
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

    static FieldsCheckedReport registrationInputsChecker(UserDto userDto){


        UserRegistrationInputValidation userRegistrationInputValidation = new UserRegistrationInputValidation();

        boolean isValidPhoneNo = true;
        boolean isValidAddress = true;
        boolean isValidDateOfBirth = true;
        boolean isValidEmail = userRegistrationInputValidation.emailValidator(userDto.getEmail());
        boolean isValidPassword = userRegistrationInputValidation.passwordValidator(userDto.getPassword());
        boolean isValidUsername = userRegistrationInputValidation.usernameValidator(userDto.getUsername());
        boolean isValidFirstName = userRegistrationInputValidation.firstNameAndLastNameValidator(userDto.getFirstName());
        boolean isValidLastName = userRegistrationInputValidation.firstNameAndLastNameValidator(userDto.getLastName());

        String status = "PASS";
        StringBuilder invalidFieldMessage = new StringBuilder();

        invalidFieldMessage.append("Invalid Fields : { ");

        if(!isValidUsername || !isValidFirstName || !isValidLastName || !isValidPassword ||
                !isValidEmail || !isValidAddress || !isValidDateOfBirth || !isValidPhoneNo){

            status = "FAILED";

            if(!isValidUsername) {
                invalidFieldMessage.append("username: failed | ");
            }
            if(!isValidFirstName) {
                invalidFieldMessage.append("first Name: failed | ");
            }
            if(!isValidLastName) {
                invalidFieldMessage.append("last Name: failed | ");
            }
            if(!isValidPassword) {
                invalidFieldMessage.append("Password: failed | ");
            }
            if(!isValidAddress) {
                invalidFieldMessage.append("Address: failed | ");
            }
            if(!isValidPhoneNo) {
                invalidFieldMessage.append("Phone number: failed | ");
            }
            if(!isValidEmail) {
                invalidFieldMessage.append("Email: failed | ");
            }
            if(!isValidDateOfBirth) {
                invalidFieldMessage.append("Date of birth: failed | ");
            }

        }

        invalidFieldMessage.append(" Message ->> double check each field entry values. }");

        return new FieldsCheckedReport(status,invalidFieldMessage.toString());
    }

    static User mapDtoToEntity(UserDto userDto){

        User userEntity = new User();

        userEntity.setId(userDto.getId());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setDateOfBirth(userDto.getDateOfBirth());
        userEntity.setAddress(userDto.getAddress());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPhoneNo(userDto.getPhoneNo());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setCreatedAt(userDto.getCreatedAt());
        userEntity.setModifiedAt(userDto.getModifiedAt());

        return userEntity;
    }

    static UserDto mapEntityToDto(User userEntity) {

        UserDto userDto = new UserDto();

        userDto.setId(userEntity.getId());
        userDto.setUsername(userEntity.getUsername());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setDateOfBirth(userEntity.getDateOfBirth());
        userDto.setAddress(userEntity.getAddress());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPhoneNo(userEntity.getPhoneNo());
        userDto.setCreatedAt(userEntity.getCreatedAt());
        userDto.setModifiedAt(userEntity.getModifiedAt());

        return userDto;
    }

    static BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
