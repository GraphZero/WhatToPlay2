package com.aa.whattoplay.games.infastructure.entities.embeddables;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ImageInfoE {
    private String imageUrl;
    private String cloudinaryId;
    private int width;
    private int height;
}
