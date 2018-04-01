package com.aa.whattoplay.games.infastructure.entities.igdb;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Developers")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class DeveloperEntity extends IgdbAbstractEntity {

    @Column(nullable = false)
    @NotNull
    private String name;

    private String url;
    private String description;
    private String website;
    private LocalDate startDate;

    @Column(name ="image_url" )
    private String developerImageUrl;

    @Column(name ="image_cloudinary_id")
    private String developerImageCloudinaryId;

    @Column(name ="image_width" )
    private int developerImageWidth;

    @Column(name ="image_height")
    private int developerImageHeight;

    public DeveloperEntity(@NotNull String name, String url, String description, String website,
                           LocalDate startDate, String developerImageUrl, String developerImageCloudinaryId, int developerImageWidth, int developerImageHeight) {
        this.name = name;
        this.url = url;
        this.description = description;
        this.website = website;
        this.startDate = startDate;
        this.developerImageUrl = developerImageUrl;
        this.developerImageCloudinaryId = developerImageCloudinaryId;
        this.developerImageWidth = developerImageWidth;
        this.developerImageHeight = developerImageHeight;
    }

    @Builder
    public DeveloperEntity(long id, @NotNull String name, String url, String description, String website,
                           LocalDate startDate, String developerImageUrl, String developerImageCloudinaryId,
                           int developerImageWidth, int developerImageHeight) {
        super(id);
        this.name = name;
        this.url = url;
        this.description = description;
        this.website = website;
        this.startDate = startDate;
        this.developerImageUrl = developerImageUrl;
        this.developerImageCloudinaryId = developerImageCloudinaryId;
        this.developerImageWidth = developerImageWidth;
        this.developerImageHeight = developerImageHeight;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeveloperEntity)) return false;
        DeveloperEntity developerEntity = (DeveloperEntity) o;
        return developerImageWidth == developerEntity.developerImageWidth &&
                developerImageHeight == developerEntity.developerImageHeight &&
                Objects.equals(name, developerEntity.name) &&
                Objects.equals(url, developerEntity.url) &&
                Objects.equals(description, developerEntity.description) &&
                Objects.equals(website, developerEntity.website) &&
                Objects.equals(startDate, developerEntity.startDate) &&
                Objects.equals(developerImageUrl, developerEntity.developerImageUrl) &&
                Objects.equals(developerImageCloudinaryId, developerEntity.developerImageCloudinaryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, description, website, startDate, developerImageUrl, developerImageCloudinaryId, developerImageWidth, developerImageHeight);
    }
}
