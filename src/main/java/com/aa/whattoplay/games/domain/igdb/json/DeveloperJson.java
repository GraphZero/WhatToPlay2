package com.aa.whattoplay.games.domain.igdb.json;

import com.aa.whattoplay.games.infastructure.entities.igdb.Developer;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;


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

    @JsonSetter("start_date")
    public void setStartDate(long startDate) {
        this.startDate = Instant.ofEpochMilli(startDate).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @JsonGetter("start_date")
    public long getStartDate() {
        if ( startDate != null ){
            ZonedDateTime zdt = startDate.atStartOfDay(ZoneId.systemDefault());
            return zdt.toInstant().toEpochMilli();
        }
        return 0;
    }

    @JsonSetter
    public void setDeveloperImageUrl(String developerImageUrl) {
        this.developerImageUrl = developerImageUrl;
    }

    @JsonSetter
    public void setDeveloperImageCloudinaryId(String developerImageCloudinaryId) {
        this.developerImageCloudinaryId = developerImageCloudinaryId;
    }

    @JsonSetter
    public void setDeveloperImageWidth(int developerImageWidth) {
        this.developerImageWidth = developerImageWidth;
    }

    @JsonSetter  
    public void setDeveloperImageHeight(int developerImageHeight) {
        this.developerImageHeight = developerImageHeight;
    }

    @JsonSetter("logo")
    public void setDeveloperImageUrl(ImageInfoJson developerImageUrl) {
        this.developerImageUrl = developerImageUrl.getImageUrl();
        this.developerImageCloudinaryId = developerImageUrl.getCloudinaryId();
        this.developerImageWidth = developerImageUrl.getWidth();
        this.developerImageHeight = developerImageUrl.getHeight();
    }

    public Developer entity(){
        return Developer.builder()
                .id(id)
                .name(name)
                .url(url)
                .description(description)
                .website(website)
                .startDate(startDate)
                .developerImageUrl(developerImageUrl)
                .developerImageCloudinaryId(developerImageCloudinaryId)
                .developerImageWidth(developerImageWidth)
                .developerImageHeight(developerImageHeight)
                .build();
    }

}
