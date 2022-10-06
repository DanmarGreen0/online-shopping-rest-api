package com.online_shopping_rest_api.security;

import com.online_shopping_rest_api.entity.User;
import com.online_shopping_rest_api.repository.RoleRepository;
import com.online_shopping_rest_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;

    static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public ApplicationUserDetailsService(UserRepository userRepository, RoleRepository authGroupRepository) {
        this.userRepository = userRepository;
        this.roleRepository = authGroupRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       final Optional <User> user = this.userRepository.findByUsername(username);

        if(!user.isPresent()) {
            throw new UsernameNotFoundException("User with the username " + username + " does not exist.");
        }

        return new ApplicationUserPrincipal(user.get());
    }
}
