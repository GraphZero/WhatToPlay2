package com.aa.whattoplay.games.infastructure.entities.igdb;

import com.aa.whattoplay.games.domain.igdb.value.External;
import com.aa.whattoplay.games.domain.igdb.value.Status;
import com.aa.whattoplay.games.infastructure.entities.embeddables.Esrb;
import com.aa.whattoplay.games.infastructure.entities.embeddables.ImageInfo;
import com.aa.whattoplay.games.infastructure.entities.embeddables.Pegi;
import com.aa.whattoplay.games.infastructure.entities.embeddables.TimeToBeat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Games")
@AllArgsConstructor
@Setter
@Getter
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "collection_id", nullable = false, referencedColumnName = "id")
    private CollectionEntity collectionEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "franchise_id", nullable = false, referencedColumnName = "id")
    private FranchiseEntity franchiseEntity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Games_Developers",
            joinColumns = {
                    @JoinColumn(name = "game_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "developer_id")
            })
    private Set<DeveloperEntity> developerEntities;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Games_Game_Modes",
            joinColumns = {
                    @JoinColumn(name = "game_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "game_mode_id")
            })
    private Set<GameModeEntity> gameModeEntities;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Games_Genres",
            joinColumns = {
                    @JoinColumn(name = "game_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "genre_id")
            })
    private Set<GenreEntity> genreEntities;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Games_Player_Perspectives",
            joinColumns = {
                    @JoinColumn(name = "game_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "player_perspective_id")
            })
    private Set<PlayerPerspectiveEntity> playerPerspectiveEntities;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "gameEntity")
    private Set<WebsiteEntity> websiteEntities;

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
                      LocalDate updatedAt, LocalDate firstReleaseDate, CollectionEntity collectionEntity, FranchiseEntity franchiseEntity,
                      Set<DeveloperEntity> developerEntities, Set<GameModeEntity> gameModeEntities, Set<GenreEntity> genreEntities, Set<PlayerPerspectiveEntity> playerPerspectiveEntities,
                      Set<WebsiteEntity> websiteEntities, Status status, TimeToBeat timeToBeat, Esrb esrb, Pegi pegi, External external, ImageInfo cover) {
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
        this.collectionEntity = collectionEntity;
        this.franchiseEntity = franchiseEntity;
        this.developerEntities = developerEntities;
        this.gameModeEntities = gameModeEntities;
        this.genreEntities = genreEntities;
        this.playerPerspectiveEntities = playerPerspectiveEntities;
        this.websiteEntities = websiteEntities;
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
        GameEntity gameEntity = (GameEntity) o;
        return Objects.equals(getId(), gameEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "GameEntity{" +
                "name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", url='" + url + '\'' +
                ", summary='" + summary + '\'' +
                ", storyline='" + storyline + '\'' +
                ", hypes=" + hypes +
                ", popularity=" + popularity +
                ", rating=" + rating +
                ", ratingCount=" + ratingCount +
                ", aggregatedRating=" + aggregatedRating +
                ", aggregatedRatingCount=" + aggregatedRatingCount +
                ", totalRating=" + totalRating +
                ", totalRatingCount=" + totalRatingCount +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", firstReleaseDate=" + firstReleaseDate +
                ", collectionEntity=" + collectionEntity.getId() +
                ", franchiseEntity=" + franchiseEntity.getId() +
                ", developersNumber=" + developerEntities.size() +
                ", gameModesNumber=" + gameModeEntities.size() +
                ", genresNumber=" + genreEntities.size() +
                ", playerPerspectivesNumber=" + playerPerspectiveEntities.size() +
                ", websitesNumber=" + websiteEntities.size() +
                ", status=" + status +
                ", timeToBeat=" + timeToBeat +
                ", esrb=" + esrb +
                ", pegi=" + pegi +
                ", external=" + external +
                ", cover=" + cover +
                '}';
    }
}