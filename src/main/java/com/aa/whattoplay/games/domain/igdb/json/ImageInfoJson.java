package com.aa.whattoplay.games.domain.igdb.json;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;


@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class ImageInfoJson {
    private String imageUrl;
    private String cloudinaryId;
    private int width;
    private int height;

    @JsonSetter("url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonGetter("cloudinary_id")
    public String getCloudinaryId() {
        return cloudinaryId;
    }

    @JsonSetter("cloudinary_id")
    public void setCloudinaryId(String cloudinaryId) {
        this.cloudinaryId = cloudinaryId;
    }

}
