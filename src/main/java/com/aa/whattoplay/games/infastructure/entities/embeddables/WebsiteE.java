package com.aa.whattoplay.games.infastructure.entities.embeddables;

import com.aa.whattoplay.games.domain.igdb.value.WebsiteCategory;
import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WebsiteE {
    private WebsiteCategory websiteCategory;
    private String url;
}
