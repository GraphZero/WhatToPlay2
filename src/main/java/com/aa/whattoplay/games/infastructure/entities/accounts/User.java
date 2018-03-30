package com.aa.whattoplay.games.infastructure.entities.accounts;

import com.aa.ddd.common.domain.AbstractEntity;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
@EqualsAndHashCode
public class User extends AbstractEntity {

}
