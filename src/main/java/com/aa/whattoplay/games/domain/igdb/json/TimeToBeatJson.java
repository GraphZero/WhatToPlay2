package com.aa.whattoplay.games.domain.igdb.json;

import com.aa.whattoplay.games.infastructure.entities.embeddables.TimeToBeat;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

import java.time.Duration;


@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class TimeToBeatJson {
    private Duration hastly;
    private Duration normally;
    private Duration completely;

    @JsonSetter("hastly")
    public void setHastly(long hastly) {
        this.hastly = Duration.ofSeconds(hastly);
    }

    @JsonSetter("normally")
    public void setNormally(long normally) {
        this.normally = Duration.ofSeconds(normally);
    }

    @JsonSetter("completely")
    public void setCompletely(long completely) {
        this.completely = Duration.ofSeconds(completely);
    }

    public TimeToBeat entity(){
        return TimeToBeat.builder()
                .hastly(hastly)
                .normally(normally)
                .completely(completely)
                .build();
    }

}
