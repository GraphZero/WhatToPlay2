package com.aa.whattoplay.games.infastructure.entities.embeddables;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExternalE {
    private String steam;
}
