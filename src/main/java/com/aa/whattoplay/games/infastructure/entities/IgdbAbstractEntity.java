package com.aa.whattoplay.games.infastructure.entities;

import com.aa.ddd.common.domain.PersistentClass;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * It's ID is set.
 */

@MappedSuperclass
@ToString
@Getter
public abstract class IgdbAbstractEntity extends PersistentClass{

    @Id
    private long id;

    public IgdbAbstractEntity() {
        super();
    }

    protected IgdbAbstractEntity(long id) {
        super();
        this.id = id;
    }

    @Override
    abstract public int hashCode();

    @Override
    abstract public boolean equals(Object obj);

}
