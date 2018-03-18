package com.aa.whattoplay.games.domain.igdb.value;

import lombok.Getter;

@Getter
public enum WebsiteCategory {
    OFFICIAL (1),
    WIKIA (2),
    WIKIPEDIA (3),
    FACEBOOK (4),
    TWITTER (5),
    TWITCH (6),
    INSTAGRAM (7),
    YOUTUBE (8),
    IPHONE (9),
    IPAD (10),
    ANDROID (11),
    STEAM (12);
    private final int category;

    WebsiteCategory(int category) {
        this.category = category;
    }


}
