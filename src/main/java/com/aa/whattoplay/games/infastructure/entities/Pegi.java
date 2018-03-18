package com.aa.whattoplay.games.infastructure.entities;

import com.aa.whattoplay.games.domain.igdb.value.PegiRating;
import lombok.Value;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Value
public class Pegi {
    @Enumerated(EnumType.STRING)
    private PegiRating pegiRating;
    private String pegiSynopsis;
}
