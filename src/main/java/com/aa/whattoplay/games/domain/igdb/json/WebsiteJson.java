package com.aa.whattoplay.games.domain.igdb.json;

import com.aa.whattoplay.games.domain.igdb.value.WebsiteCategory;
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

}
