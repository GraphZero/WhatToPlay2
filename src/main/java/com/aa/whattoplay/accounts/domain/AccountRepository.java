package com.aa.whattoplay.accounts.domain;

import com.aa.whattoplay.accounts.infastructure.entities.AccountEntity;

public interface AccountRepository {
    AccountEntity save(AccountEntity account);
}
