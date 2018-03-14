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

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Test
    void name() {
        // given
        // when
        // then
        String test = "abrakadabra";
        Map<Character, Long> map = test
                .chars()
                .boxed()
                .collect(Collectors.groupingBy(( x -> (char) x.intValue()), Collectors.counting()));
        System.out.println(map);
    }
}