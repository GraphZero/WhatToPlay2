package com.aa.whattoplay.games.infastructure.entities;

import com.aa.ddd.common.domain.AbstractEntity;
import com.aa.whattoplay.games.domain.igdb.value.WebsiteCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Websites")
@NoArgsConstructor @Getter @Setter
public class WebsiteEntity extends AbstractEntity {

    @Enumerated(value = EnumType.STRING)
    private WebsiteCategory websiteCategory;
    private String url;

    @Builder
    public WebsiteEntity(long id, WebsiteCategory websiteCategory, String url, GameEntity game) {
        super(id);
        this.websiteCategory = websiteCategory;
        this.url = url;
        this.game = game;
    }

    @ManyToOne
    @JoinColumn(name="game_id", nullable = false, referencedColumnName = "id")
    private GameEntity game;

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
