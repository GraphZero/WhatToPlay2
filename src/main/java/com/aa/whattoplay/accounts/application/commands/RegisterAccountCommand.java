package com.aa.whattoplay.accounts.application.commands;

import com.aa.ddd.common.annotations.Command;
import lombok.Value;

@Command
@Value
public class RegisterAccountCommand {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
}
