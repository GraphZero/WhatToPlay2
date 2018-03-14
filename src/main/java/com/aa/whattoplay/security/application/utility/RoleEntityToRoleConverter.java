package com.aa.whattoplay.security.application.utility;

import com.aa.whattoplay.security.domain.Role;
import com.aa.whattoplay.security.infastructure.entities.RoleEntity;
import java.util.Collection;
import java.util.stream.Collectors;

public class RoleEntityToRoleConverter {

    public static Role convert(RoleEntity roleEntity){
        return new Role(roleEntity.getId(),
                roleEntity.getRoleName(),
                roleEntity.getRoleNumber(),
                roleEntity.getUser().getId(),
                UserEntityToUserConverter.convert(roleEntity.getUser()));
    }

    public static Collection<Role> convertAll(Collection<RoleEntity> fElements){
        return fElements.stream()
                        .map(RoleEntityToRoleConverter::convert)
                        .collect(Collectors.toList());
    }


}
