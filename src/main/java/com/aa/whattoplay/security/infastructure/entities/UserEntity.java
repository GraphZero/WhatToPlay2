package com.aa.whattoplay.security.infastructure.entities;

import com.aa.ddd.common.domain.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "Users")
@Getter @AllArgsConstructor
public class UserEntity extends AbstractEntity{

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<RoleEntity> roles;

    public UserEntity() {
        super();
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
