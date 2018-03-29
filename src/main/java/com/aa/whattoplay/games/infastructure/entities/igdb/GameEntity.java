package com.aa.whattoplay.games.infastructure.entities.igdb;

import com.aa.whattoplay.games.domain.igdb.value.External;
import com.aa.whattoplay.games.domain.igdb.value.Status;
import com.aa.whattoplay.games.infastructure.entities.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Games")
@AllArgsConstructor
@Setter @Getter
public class GameEntity extends IgdbAbstractEntity {
    @Column(nullable = false)
    @NotEmpty
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
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate firstReleaseDate;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false, referencedColumnName = "id")
    private Collection collection;

    @ManyToOne
    @JoinColumn(name = "franchise_id", nullable = false, referencedColumnName = "id")
    private Franchise franchise;

    @ManyToMany
    @JoinTable(name="Games_Developers")
    private Set<Developer> developers;

    @ManyToMany
    @JoinTable(name="Games_Game_Modes")
    private Set<GameModeEntity> gameModes;

    @ManyToMany
    @JoinTable(name="Games_Genres")
    private Set<Genre> genres;

    @ManyToMany
    @JoinTable(name="Games_Player_Perspectives")
    private Set<PlayerPerspective> playerPerspectives;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "game")
    private Set<WebsiteEntity> websites;

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

    @Builder
    public GameEntity(long id, @NotNull String name, String slug, String url, String summary, String storyline,
                      int hypes, double popularity, double rating, int ratingCount, double aggregatedRating,
                      int aggregatedRatingCount, double totalRating, int totalRatingCount, LocalDate createdAt,
                      LocalDate updatedAt, LocalDate firstReleaseDate, Collection collection, Franchise franchise,
                      Set<Developer> developers, Set<GameModeEntity> gameModes, Set<Genre> genres, Set<PlayerPerspective> playerPerspectives,
                      Set<WebsiteEntity> websites, Status status, TimeToBeat timeToBeat, Esrb esrb, Pegi pegi, External external, ImageInfo cover) {
        super(id);
        this.name = name;
        this.slug = slug;
        this.url = url;
        this.summary = summary;
        this.storyline = storyline;
        this.hypes = hypes;
        this.popularity = popularity;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.aggregatedRating = aggregatedRating;
        this.aggregatedRatingCount = aggregatedRatingCount;
        this.totalRating = totalRating;
        this.totalRatingCount = totalRatingCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.firstReleaseDate = firstReleaseDate;
        this.collection = collection;
        this.franchise = franchise;
        this.developers = developers;
        this.gameModes = gameModes;
        this.genres = genres;
        this.playerPerspectives = playerPerspectives;
        this.websites = websites;
        this.status = status;
        this.timeToBeat = timeToBeat;
        this.esrb = esrb;
        this.pegi = pegi;
        this.external = external;
        this.cover = cover;
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