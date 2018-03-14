package com.aa.whattoplay.accounts.application;

import com.aa.whattoplay.accounts.application.commands.RegisterAccountCommand;
import com.aa.whattoplay.accounts.domain.AccountRepository;
import com.aa.whattoplay.accounts.infastructure.entities.AccountEntity;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
class AccountControlServiceTest {
    @Autowired
    private AccountControlService accountControlService;

    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    void shouldRegisterAccount() {
        // given
        RegisterAccountCommand registerAccountCommand
                = new RegisterAccountCommand("a12", "b12", "c12", "d12", "e12");
        // when
        AccountEntity accountEntity = accountControlService.registerAccount(registerAccountCommand);
        // then
        assertEquals("c12", testEntityManager.find(AccountEntity.class, accountEntity.getId()).getUsername());
    }

    @Test
    void shouldEncodePassword() {
        // given
        RegisterAccountCommand registerAccountCommand
                = new RegisterAccountCommand("a12", "b12", "c12", "d12", "e12");
        // when
        AccountEntity accountEntity = accountControlService.registerAccount(registerAccountCommand);
        // then
        assertTrue(testEntityManager.find(AccountEntity.class, accountEntity.getId()).getPassword().length() > 15);
    }

}