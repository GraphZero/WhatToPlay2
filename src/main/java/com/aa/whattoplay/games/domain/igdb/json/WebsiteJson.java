package com.aa.whattoplay.games.domain.igdb.json;

import com.aa.whattoplay.games.domain.igdb.value.WebsiteCategory;
import com.aa.whattoplay.games.infastructure.entities.igdb.Game;
import com.aa.whattoplay.games.infastructure.entities.igdb.Website;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class WebsiteJson {
    private WebsiteCategory websiteCategory;
    private String url;

    @JsonSetter("category")
    public void setWebsiteCategory(String websiteCategory) {
        for ( WebsiteCategory e : WebsiteCategory.values()){
            if ( e.toString().equals(websiteCategory) ){
                this.websiteCategory = e;
                return;
            }
        }
        this.websiteCategory = WebsiteCategory.OFFICIAL;
    }

    public Website entity(Game game){
        return Website.builder()
                .game(game)
                .websiteCategory(websiteCategory)
                .url(url)
                .build();
    }

}
