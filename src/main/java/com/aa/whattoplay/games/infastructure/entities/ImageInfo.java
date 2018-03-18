package com.aa.whattoplay.games.infastructure.entities;

import lombok.Builder;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
@Builder
public class ImageInfo {
    private String imageUrl;
    private String cloudinaryId;
    private int width;
    private int height;

}
