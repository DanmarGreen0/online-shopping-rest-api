package com.project.portfolio.springboot.onlinesellingandshopping.serverapp.security;
import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.Entity.AuthGroup;
import com.project.portfolio.springboot.onlinesellingandshopping.serverapp.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class ApplicationUserPrincipal implements UserDetails {

    private final User user;
    private final List<AuthGroup> authGroups;

    public ApplicationUserPrincipal(User user, List<AuthGroup> authGroups) {
        super();
        this.user = user;
        this.authGroups = authGroups;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(null==authGroups){
            return Collections.emptySet();
        }
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
        authGroups.forEach(authGroup ->{
            grantedAuthorities.add(new SimpleGrantedAuthority(authGroup.getAuthGroup()));
        });
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
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
