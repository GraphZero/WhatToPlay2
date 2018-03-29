package com.aa.functional;

import com.aa.whattoplay.accounts.application.AccountControlService;
import com.aa.whattoplay.accounts.application.commands.RegisterAccountCommand;
import com.aa.whattoplay.accounts.infastructure.entities.AccountEntity;
import com.aa.whattoplay.security.application.UserAuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
public class UserLoginTest {
    @Autowired
    private AccountControlService accountControlService;

    @Autowired
    private UserAuthService userAuthService;

    @Test
    void shouldBeAbleToLogin() {
        // given
        RegisterAccountCommand registerAccountCommand
                = new RegisterAccountCommand("a12", "b12", "c12", "d12", "e12");
        // when
        AccountEntity accountEntity = accountControlService.registerAccount(registerAccountCommand);
        // then
        assertEquals( "c12", userAuthService.loadUserByUsername("c12").getUsername());
    }


}
