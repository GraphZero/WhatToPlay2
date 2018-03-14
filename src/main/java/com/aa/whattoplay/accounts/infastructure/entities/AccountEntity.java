package com.aa.whattoplay.accounts.infastructure.entities;

import com.aa.ddd.common.domain.AbstractEntity;
import com.aa.whattoplay.security.infastructure.entities.RoleEntity;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "Users")
@Getter
public class AccountEntity extends AbstractEntity {

    @Size(min = 3, max = 64 )
    private String firstName;

    @Size(min = 3, max = 64 )
    private String lastName;

    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 32 )
    private String username;

    @Column(nullable = false)
    @Size(min = 6, max = 64 )
    private String password;

    @Column(nullable = false)
    @Size(min = 3, max = 32 )
    private String email;

    @Column(nullable = false)
    private boolean enabled;

    public AccountEntity() {
        super();
    }

    public AccountEntity(@Size(min = 3, max = 64) String firstName,
                         @Size(min = 3, max = 64) String lastName,
                         @Size(min = 2, max = 32) String username,
                         @Size(min = 6, max = 64) String password,
                         @Size(min = 3, max = 32) String email,
                         boolean enabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
