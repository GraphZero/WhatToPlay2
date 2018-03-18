package com.aa.whattoplay.games.infastructure.entities;

import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value
public class ImageInfo {
    private String imageUrl;
    private String cloudinaryId;
    private int width;
    private int height;
}
