package com.aa.whattoplay.games.infastructure.entities;

import com.aa.ddd.common.domain.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Developers")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Developer extends AbstractEntity {

    @Column(nullable = false)
    @NotNull
    private String name;

    private String url;
    private String description;
    private String website;
    private LocalDate startDate;

    @Column(name ="image_url", nullable = true )
    private String developerImageUrl;

    @Column(name ="image_cloudinary_id", nullable = true )
    private String developerImageCloudinaryId;

    @Column(name ="image_width", nullable = true )
    private int developerImageWidth;

    @Column(name ="image_height", nullable = true )
    private int developerImageHeight;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Developer)) return false;
        Developer developer = (Developer) o;
        return developerImageWidth == developer.developerImageWidth &&
                developerImageHeight == developer.developerImageHeight &&
                Objects.equals(name, developer.name) &&
                Objects.equals(url, developer.url) &&
                Objects.equals(description, developer.description) &&
                Objects.equals(website, developer.website) &&
                Objects.equals(startDate, developer.startDate) &&
                Objects.equals(developerImageUrl, developer.developerImageUrl) &&
                Objects.equals(developerImageCloudinaryId, developer.developerImageCloudinaryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, description, website, startDate, developerImageUrl, developerImageCloudinaryId, developerImageWidth, developerImageHeight);
    }
}