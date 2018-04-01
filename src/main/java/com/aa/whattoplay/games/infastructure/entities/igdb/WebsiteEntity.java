package com.aa.whattoplay.games.infastructure.entities.igdb;

import com.aa.ddd.common.domain.AbstractEntity;
import com.aa.whattoplay.games.domain.igdb.value.WebsiteCategory;
import com.aa.whattoplay.games.domain.suggestions.value.Website;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Websites")
@NoArgsConstructor
@Getter
@Setter
public class WebsiteEntity extends AbstractEntity {

    @Enumerated(value = EnumType.STRING)
    private WebsiteCategory websiteCategory;
    private String url;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false, referencedColumnName = "id")
    private GameEntity gameEntity;

    @Builder
    public WebsiteEntity(WebsiteCategory websiteCategory, String url, GameEntity gameEntity) {
        this.websiteCategory = websiteCategory;
        this.url = url;
        this.gameEntity = gameEntity;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    public Website value(){
        return Website.builder()
                .id(getId())
                .websiteCategory(websiteCategory)
                .url(url)
                .gameEntity(gameEntity)
                .build();
    }

}
