package com.aa.whattoplay.security.domain;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class User implements UserDetails {
    private final String username;
    private final String password;
    private final boolean enabled;
    private final Collection<Role> roles;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        roles.forEach( role ->  authorityList.add( () -> ("ROLE_" + role.getRoleName().replace(" ", "")) ));
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password.trim().replaceAll(" " , "");
    }

    @Override
    public String getUsername() {
        return username.trim().replaceAll(" " , "");
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
        return enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }
}
