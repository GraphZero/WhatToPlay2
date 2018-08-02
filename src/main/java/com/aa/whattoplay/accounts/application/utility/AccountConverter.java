package com.aa.whattoplay.accounts.application.utility;

import com.aa.whattoplay.accounts.domain.Account;
import com.aa.whattoplay.accounts.infastructure.entities.AccountEntity;

import java.util.Collection;
import java.util.stream.Collectors;

public class AccountConverter {

    public static Account convertToAccount(AccountEntity accountEntity){

        return new Account(accountEntity.getId(),
                accountEntity.getFirstName(),
                accountEntity.getLastName(),
                accountEntity.getUsername(),
                accountEntity.getPassword(),
                accountEntity.getEmail(),
                accountEntity.isEnabled());
    }

    public static Collection<Account> convertAllToAccount(Collection<AccountEntity> fElements){
        return fElements.stream()
                .map(AccountConverter::convertToAccount)
                .collect(Collectors.toList());
    }



}
