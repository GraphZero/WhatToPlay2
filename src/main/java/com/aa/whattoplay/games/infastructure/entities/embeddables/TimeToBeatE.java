package com.aa.whattoplay.games.infastructure.entities.embeddables;

import javax.persistence.Embeddable;
import java.time.Duration;

@Embeddable
public class TimeToBeatE {
    private Duration hastly;
    private Duration normally;
    private Duration completely;
}
