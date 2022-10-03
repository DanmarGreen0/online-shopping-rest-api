package com.project.portfolio.springboot.onlinesellingandshopping.serverapp.security;

import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.Entity.AuthGroup;
import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.Entity.User;
import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.repository.AuthGroupRepository;
import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthGroupRepository authGroupRepository;

    public ApplicationUserDetailsService(UserRepository userRepository, AuthGroupRepository authGroupRepository) {
        this.userRepository = userRepository;
        this.authGroupRepository = authGroupRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) this.userRepository.findByUsername(username).get();

        if(null==user){
            throw new UsernameNotFoundException("couldn't find username: " + username);
        }

        List<AuthGroup> authGroups = this.authGroupRepository.findByUsername(username);

        return new ApplicationUserPrincipal(user, authGroups);
    }
}
