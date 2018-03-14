package com.aa.whattoplay.accounts.application;

import com.aa.ddd.common.annotations.ApplicationService;
import com.aa.whattoplay.accounts.application.commands.RegisterAccountCommand;
import com.aa.whattoplay.accounts.domain.AccountRepository;
import com.aa.whattoplay.accounts.infastructure.entities.AccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@ApplicationService
@RequiredArgsConstructor
public class AccountControlService implements IAccountControlService {
    private final AccountRepository accountRepository;

    @Override
    public AccountEntity registerAccount(RegisterAccountCommand registerAccountCommand) {
        AccountEntity accountEntity = new AccountEntity(
                registerAccountCommand.getFirstName().trim(),
                registerAccountCommand.getLastName().trim(),
                registerAccountCommand.getUsername().trim(),
                encoder().encode(registerAccountCommand.getPassword()).trim(),
                registerAccountCommand.getEmail().trim(),
                true
        );
        return accountRepository.save(accountEntity);
    }

    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

}
