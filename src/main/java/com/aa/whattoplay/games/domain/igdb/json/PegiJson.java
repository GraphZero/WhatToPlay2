package com.aa.whattoplay.games.domain.igdb.json;

import com.aa.whattoplay.games.domain.igdb.value.PegiRating;
import com.aa.whattoplay.games.infastructure.entities.embeddables.Pegi;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class PegiJson {
    private PegiRating pegiRating;
    private String pegiSynopsis;

    @JsonSetter("rating")
    public void setPegiRating(String pegiRating) {
        for ( PegiRating e : PegiRating.values()){
            if ( e.toString().equals(pegiRating)){
                this.pegiRating = e;
                return;
            }
        }
        this.pegiRating = PegiRating.EIGHTEEN;
    }

    @JsonSetter("synopsis")
    public void setPegiSynopsis(String pegiSynopsis) {
        this.pegiSynopsis = pegiSynopsis;
    }


    public Pegi entity(){
        return Pegi.builder()
                .pegiRating(pegiRating)
                .pegiSynopsis(pegiSynopsis)
                .build();
    }

}
