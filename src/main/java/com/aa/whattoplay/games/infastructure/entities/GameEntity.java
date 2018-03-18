package com.aa.whattoplay.games.infastructure.entities;

import com.aa.ddd.common.domain.AbstractEntity;
import com.aa.whattoplay.games.domain.igdb.value.External;
import com.aa.whattoplay.games.domain.igdb.value.Status;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Games")
@AllArgsConstructor
public class GameEntity extends AbstractEntity implements Serializable {
    @Column(nullable = false)
    @NotNull
    private String name;

    private String slug;
    private String url;
    private String summary;
    private String storyline;
    private int hypes;
    private double popularity;
    private double rating;
    private int ratingCount;
    private double aggregatedRating;
    private int aggregatedRatingCount;
    private double totalRating;
    private int totalRatingCount;


    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false, referencedColumnName = "id")
    private Collection collection;

    @ManyToOne
    @JoinColumn(name = "franchise_id", nullable = false, referencedColumnName = "id")
    private Franchise franchise;

    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate firstReleaseDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    private TimeToBeat timeToBeat;

    @Embedded
    private Esrb esrb;

    @Embedded
    private Pegi pegi;

    @Embedded
    private External external;

    @Embedded
    private ImageInfo cover;

    public GameEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameEntity)) return false;
        GameEntity that = (GameEntity) o;
        return hypes == that.hypes &&
                Double.compare(that.popularity, popularity) == 0 &&
                Double.compare(that.rating, rating) == 0 &&
                ratingCount == that.ratingCount &&
                Double.compare(that.aggregatedRating, aggregatedRating) == 0 &&
                aggregatedRatingCount == that.aggregatedRatingCount &&
                Double.compare(that.totalRating, totalRating) == 0 &&
                totalRatingCount == that.totalRatingCount &&
                Objects.equals(name, that.name) &&
                Objects.equals(slug, that.slug) &&
                Objects.equals(url, that.url) &&
                Objects.equals(summary, that.summary) &&
                Objects.equals(storyline, that.storyline) &&
                Objects.equals(collection, that.collection) &&
                Objects.equals(franchise, that.franchise) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(firstReleaseDate, that.firstReleaseDate) &&
                status == that.status &&
                Objects.equals(timeToBeat, that.timeToBeat) &&
                Objects.equals(esrb, that.esrb) &&
                Objects.equals(pegi, that.pegi) &&
                Objects.equals(external, that.external) &&
                Objects.equals(cover, that.cover);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, slug, url, summary, storyline, hypes, popularity, rating, ratingCount, aggregatedRating, aggregatedRatingCount, totalRating, totalRatingCount, collection, franchise, createdAt, updatedAt, firstReleaseDate, status, timeToBeat, esrb, pegi, external, cover);
    }
}