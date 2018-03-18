package com.aa.whattoplay.games.domain.igdb.value;

import lombok.Getter;

@Getter
public enum PegiRating {
    THREE(1), SEVEN(2), TWELVE(3), SIXTEEN(4), EIGHTEEN(5);
    private final int pegiRating;

    PegiRating(int pegiRating) {
        this.pegiRating = pegiRating;
    }

}
