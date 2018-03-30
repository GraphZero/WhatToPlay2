package com.aa.whattoplay.games.infastructure.entities.embeddables;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.persistence.Embeddable;
import java.time.Duration;

@Embeddable
@Value @Builder @AllArgsConstructor
public class TimeToBeat {
    private Duration hastly;
    private Duration normally;
    private Duration completely;

    public TimeToBeat() {
        hastly = Duration.ZERO;
        normally = Duration.ZERO;
        completely = Duration.ZERO;
    }
}
