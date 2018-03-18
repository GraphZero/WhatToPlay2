package com.aa.whattoplay.games.infastructure.entities.embeddables;

import com.aa.whattoplay.games.domain.igdb.value.PegiRating;
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
public class PegiE {
    @Enumerated(EnumType.STRING)
    private PegiRating pegiRating;
    private String pegiSynopsis;

}
