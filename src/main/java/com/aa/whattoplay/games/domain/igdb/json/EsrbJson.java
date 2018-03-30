package com.aa.whattoplay.games.domain.igdb.json;

import com.aa.whattoplay.games.domain.igdb.value.EsrbRating;
import com.aa.whattoplay.games.infastructure.entities.embeddables.Esrb;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EsrbJson {
    private EsrbRating esrbRating;
    private String synopsis;

    @JsonSetter("rating")
    public void setEsrbRating(String esrbRating) {
        for ( EsrbRating e : EsrbRating.values()){
            if ( e.toString().equals(esrbRating)){
                this.esrbRating = e;
                return;
            }
        }
        this.esrbRating = EsrbRating.UNKNOWN;
    }

    public Esrb entity(){
        return Esrb.builder()
                .esrbRating(esrbRating)
                .esrbSynopsis(synopsis)
                .build();
    }

}
