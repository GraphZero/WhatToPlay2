package com.aa.whattoplay.games.infastructure.entities;

import com.aa.whattoplay.games.domain.igdb.value.PegiRating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Value @Builder @AllArgsConstructor
public class Pegi {
    @Enumerated(EnumType.STRING)
    private PegiRating pegiRating;
    private String pegiSynopsis;

    public Pegi() {
        pegiRating = PegiRating.SIXTEEN;
        pegiSynopsis = "";
    }
}
