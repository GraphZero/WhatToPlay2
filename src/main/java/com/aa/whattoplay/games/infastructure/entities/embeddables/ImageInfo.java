package com.aa.whattoplay.games.infastructure.entities.embeddables;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Embeddable;

@Embeddable
@Value @Builder @AllArgsConstructor
public class ImageInfo {
    private String imageUrl;
    private String cloudinaryId;
    private int width;
    private int height;

    public ImageInfo() {
        imageUrl = "";
        cloudinaryId = "";
        width = 0;
        height = 0;
    }

}
