package com.aa.whattoplay.games.infastructure.entities.embeddables;

import com.aa.whattoplay.games.domain.igdb.value.EsrbRating;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EsrbE {
    @Enumerated(EnumType.STRING)
    private EsrbRating esrbRating;
    private String esrbSynopsis;
}
