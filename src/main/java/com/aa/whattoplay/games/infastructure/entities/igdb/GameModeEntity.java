package com.aa.whattoplay.games.infastructure.entities.igdb;

import com.aa.whattoplay.games.domain.suggestions.value.GameMode;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

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


    public GameMode value(){
        return GameMode.builder()
                .id(getId())
                .name(name)
                .url(url)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

}
