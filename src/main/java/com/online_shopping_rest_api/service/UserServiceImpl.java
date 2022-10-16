package com.online_shopping_rest_api.service;

import com.online_shopping_rest_api.dto.UserDTO;
import com.online_shopping_rest_api.entity.Role;
import com.online_shopping_rest_api.entity.User;
import com.online_shopping_rest_api.repository.RoleRepository;
import com.online_shopping_rest_api.repository.UserRepository;
import com.online_shopping_rest_api.util.DateGenerator;
import com.online_shopping_rest_api.util.OperationAuthorizationChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.String.valueOf;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    //remember to delete
    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    //To do: 1) use dto class here in the controller and not in the service classes. 2) doc exceptions

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public User createAdminUser(UserDTO userDTO) throws Exception {

        //To do: document exception
        Role adminRole = roleRepository.findByRole("ROLE_ADMIN").
                orElseThrow(() -> new ResourceNotFoundException("Admin account couldn't be create " +
                        "because the role admin isn't supported or found in database."));
        //To do: document exception
        Role userRole = roleRepository.findByRole("ROLE_USER").
                orElseThrow(() -> new ResourceNotFoundException("User account couldn't be create " +
                        "because the role user isn't supported or found in database."));

        List<Role> roles = new ArrayList<>();
        roles.add(adminRole);
        roles.add(userRole);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        DateGenerator dateGenerator = new DateGenerator();

        User user = new User.Builder(userDTO.getUsername(),userDTO.getFirstName(),userDTO.getLastName()
                ,bCryptPasswordEncoder.encode(userDTO.getPassword()),userDTO.getAddress(),userDTO.getDateOfBirth(),userDTO.getEmail()
                ,userDTO.getPhoneNo(),dateGenerator.getSQLDate(),dateGenerator.getSQLDate(), roles).build();


        return userRepository.saveAndFlush(user);
    }

    @Override
    public String updateUser(Authentication authentication, Map<String,Object> accountChanges, int id){

        Optional<UserDTO> userOnRecord = userRepository.findById(id);


        boolean isUserAuthorized = new OperationAuthorizationChecker(userOnRecord.get(),authentication,accountChanges).check();
        DateGenerator dateGenerator = new DateGenerator();

        if(!userOnRecord.isPresent()){

            throw new IllegalArgumentException("User with id = " + id);
        }

        if(isUserAuthorized){
            accountChanges.forEach((field,change)->{

                switch(field){
                    case "username":
                        userOnRecord.get().setUsername(valueOf(change));
                        break;
                    case "firstName":
                        userOnRecord.get().setFirstName(valueOf(change));
                        break;
                    case "lastName":
                        userOnRecord.get().setLastName(valueOf(change));
                        break;
                    case "password":
                        userOnRecord.get().setPassword(valueOf(change));
                        break;
                    case "address":
                        userOnRecord.get().setAddress(valueOf(change));
                        break;
                    case "dateOfBirth":
                        userOnRecord.get().setDateOfBirth(valueOf(change));
                        break;
                    case "email":
                        userOnRecord.get().setEmail(valueOf(change));
                        break;
                    case "phoneNo":
                        userOnRecord.get().setPhoneNo(valueOf(change));
                        break;
                    default:
                }
            });

            userOnRecord.get().setModifiedAt(dateGenerator.getSQLDate());
            userRepository.save(userOnRecord.get());

        }else{
           throw new IllegalArgumentException("It's forbidden to edit another User Account");
        }

        return userOnRecord.get().getId().toString();
    }

    @Override
    public String deleteUser(Authentication authentication, int id) {
        Optional<User> userOnRecord = userRepository.findById(id);
        boolean isUserAuthorized = new OperationAuthorizationChecker(userOnRecord.get(), authentication).check();

        if(!isUserAuthorized){
            throw new IllegalArgumentException("It's forbidden to delete another User Account");
        }

        try {
            userRepository.deleteById(id);
        }catch(IllegalArgumentException ex){
            throw new IllegalArgumentException("User account with id = " + id + "doesn't exist");
        }

        return "Account with id = " + id + "is now deleted";
    }

    @Override
    public Optional<User> getUser(int id) throws AccountNotFoundException {

        Optional<User> user = this.userRepository.findById(id);

        if(!user.isPresent())
            throw new AccountNotFoundException("Could not be found a user account associated with the id = " + id + ".");

        return user;
    }
    @Override
    public List<User> getUsers(){
        return this.userRepository.findAll();
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
