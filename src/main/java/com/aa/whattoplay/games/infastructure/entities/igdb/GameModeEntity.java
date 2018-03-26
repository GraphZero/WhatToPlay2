package com.aa.whattoplay.games.infastructure.entities.igdb;

import com.aa.ddd.common.domain.AbstractEntity;
import com.aa.whattoplay.games.infastructure.entities.IgdbAbstractEntity;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Entity
@Table(name = "Game_Modes")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class GameModeEntity extends IgdbAbstractEntity {
    private String name;
    private String url;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @Builder
    public GameModeEntity(long id, String name, String url, LocalDate createdAt, LocalDate updatedAt) {
        super(id);
        this.name = name;
        this.url = url;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
