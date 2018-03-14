package com.aa.whattoplay.security.infastructure.entities;

import com.aa.ddd.common.domain.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import javax.persistence.*;

@Entity
@Table(name = "Roles")
@Getter @AllArgsConstructor
public class RoleEntity extends AbstractEntity{

    @Column(nullable = false)
    private String roleName;

    @Column(nullable = false)
    private int roleNumber;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", insertable=false, updatable=false)
    private UserEntity user;

    public RoleEntity() {
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
