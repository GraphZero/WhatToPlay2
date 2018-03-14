package com.aa.whattoplay.accounts.application;

import com.aa.whattoplay.accounts.application.commands.RegisterAccountCommand;
import com.aa.whattoplay.accounts.infastructure.entities.AccountEntity;

public interface IAccountControlService {
    AccountEntity registerAccount(RegisterAccountCommand account);
}
