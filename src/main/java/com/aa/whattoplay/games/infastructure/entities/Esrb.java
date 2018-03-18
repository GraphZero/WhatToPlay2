package com.aa.whattoplay.games.infastructure.entities;

import com.aa.whattoplay.games.domain.igdb.value.EsrbRating;
import lombok.Value;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Value
public class Esrb {
    @Enumerated(EnumType.STRING)
    private EsrbRating esrbRating;
    private String esrbSynopsis;
}
