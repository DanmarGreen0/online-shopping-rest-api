package com.online_shopping_rest_api.security;

import com.online_shopping_rest_api.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ApplicationUserPrincipal implements UserDetails {
    private final UserDTO userDTO;
    public ApplicationUserPrincipal(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(userDTO == null){
            return Collections.emptySet();
        }
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
        userDTO.getRoles().forEach(role ->{
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        });
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.userDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userDTO.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
