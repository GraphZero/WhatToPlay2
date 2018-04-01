package com.aa.whattoplay.games.domain.suggestions.value;

import com.aa.ddd.common.annotations.ValueObject;
import com.aa.whattoplay.games.domain.igdb.value.WebsiteCategory;
import com.aa.whattoplay.games.infastructure.entities.igdb.GameEntity;
import lombok.Builder;
import lombok.Value;

@ValueObject
@Value
@Builder
public class Website {
    private long id;
    private WebsiteCategory websiteCategory;
    private String url;
    private GameEntity gameEntity;
}
