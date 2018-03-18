package com.aa.whattoplay.security.application;

import com.aa.ddd.common.annotations.DomainService;
import com.aa.whattoplay.security.application.utility.UserEntityToUserConverter;
import com.aa.whattoplay.security.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@DomainService
public class UserAuthService implements UserDetailsService {
    private UserRepository userRepository;
    
    public UserAuthService(){
        super();
    }
    
    @Autowired
    public void setUserDatabaseService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(UserEntityToUserConverter::convert)
                .orElseThrow(
                () -> new UsernameNotFoundException(username)
        );
    }

}
