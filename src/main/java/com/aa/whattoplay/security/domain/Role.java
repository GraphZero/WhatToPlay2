package com.aa.whattoplay.security.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter @ToString
public class Role {
    private long roleId;
    private String roleName;
    private int roleNumber;
    private long userId;
    private User user;
}
