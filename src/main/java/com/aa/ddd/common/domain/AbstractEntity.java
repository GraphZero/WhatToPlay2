package com.aa.ddd.common.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
@ToString
@Getter
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Version
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int version;

    protected final LocalDate creationDate;

    protected AbstractEntity(long id) {
        this();
        this.id = id;
    }

    public AbstractEntity() {
        creationDate = LocalDate.now();
    }

    @Override
    abstract public int hashCode();

    @Override
    abstract public boolean equals(Object obj);

}
