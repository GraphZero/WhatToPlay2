package com.aa.whattoplay.games.infastructure.entities;

import lombok.Value;

import javax.persistence.Embeddable;
import java.time.Duration;

@Embeddable
@Value
public class TimeToBeat {
    private Duration hastly;
    private Duration normally;
    private Duration completely;
}
