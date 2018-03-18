package com.aa.whattoplay.games.domain.igdb.json;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperJson {
    private long id;
    private String name;
    private String url;
    private String description;
    private String website;
    private LocalDate startDate;
    private String developerImageUrl;
    private String developerImageCloudinaryId;
    private int developerImageWidth;
    private int developerImageHeight;

    @JsonGetter("start_date")
    public LocalDate getStartDate() {
        return startDate;
    }

    @JsonSetter("start_date")
    public void setStartDate(long startDate) {
        this.startDate = Instant.ofEpochMilli(startDate).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @JsonSetter("logo")
    public void setDeveloperImageUrl(ImageInfoJson developerImageUrl) {
        this.developerImageUrl = developerImageUrl.getImageUrl();
        this.developerImageCloudinaryId = developerImageUrl.getCloudinaryId();
        this.developerImageWidth = developerImageUrl.getWidth();
        this.developerImageHeight = developerImageUrl.getHeight();
    }
}