package com.aa.whattoplay.security.application.utility;

import com.aa.ddd.common.annotations.ApplicationService;
import com.aa.whattoplay.security.domain.User;
import com.aa.whattoplay.security.infastructure.entities.UserEntity;

import java.util.Collection;
import java.util.stream.Collectors;

@ApplicationService
public class UserEntityToUserConverter {

    public static User convert(UserEntity userEntity){
        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                RoleEntityToRoleConverter.convertAll(userEntity.getRoles()) );
    }

    public static Collection<User> convertAll(Collection<UserEntity> fElements){
        return fElements.stream()
                .map(UserEntityToUserConverter::convert)
                .collect(Collectors.toList());
    }

}
