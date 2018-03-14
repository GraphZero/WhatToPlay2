package com.aa.whattoplay.security.application;

import com.aa.whattoplay.security.domain.UserRepository;
import com.aa.whattoplay.security.infastructure.entities.UserEntity;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserAuthServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserAuthService userAuthService;

    @BeforeEach
    void setUpUserAuthService(){
        userAuthService = new UserAuthService();
    }

    @Test
    void shouldLoadUserByUsername() {
        // given
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(new UserEntity(
                "a",
                "b",
                "d",
                "f",
                "e",
                true,
                Collections.emptySet()
        )));
        userAuthService.setUserDatabaseService(userRepository);
        // when
        UserDetails userDetails = userAuthService.loadUserByUsername("a");
        // then
        assertEquals( "d", userDetails.getUsername(), "Didnt load user.");
    }
}