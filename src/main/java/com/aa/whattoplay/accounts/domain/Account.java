package com.aa.whattoplay.accounts.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Account {
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private boolean enabled;
}
