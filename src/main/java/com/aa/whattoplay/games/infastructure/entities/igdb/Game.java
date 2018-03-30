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
import java.util.Set;

@Entity
@Table(name = "Games")
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class Game extends IgdbAbstractEntity {
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
    private Collection collection;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "franchise_id", nullable = false, referencedColumnName = "id")
    private Franchise franchise;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Games_Developers",
            joinColumns = {
                    @JoinColumn(name = "game_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "developer_id")
            })
    private Set<Developer> developers;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Games_Game_Modes",
            joinColumns = {
                    @JoinColumn(name = "game_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "game_mode_id")
            })
    private Set<GameMode> gameModes;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Games_Genres",
            joinColumns = {
                    @JoinColumn(name = "game_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "genre_id")
            })
    private Set<Genre> genres;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Games_Player_Perspectives",
            joinColumns = {
                    @JoinColumn(name = "game_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "player_perspective_id")
            })
    private Set<PlayerPerspective> playerPerspectives;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "game")
    private Set<Website> websites;

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

    public Game() {
    }

    @Builder
    public Game(long id, @NotNull String name, String slug, String url, String summary, String storyline,
                int hypes, double popularity, double rating, int ratingCount, double aggregatedRating,
                int aggregatedRatingCount, double totalRating, int totalRatingCount, LocalDate createdAt,
                LocalDate updatedAt, LocalDate firstReleaseDate, Collection collection, Franchise franchise,
                Set<Developer> developers, Set<GameMode> gameModes, Set<Genre> genres, Set<PlayerPerspective> playerPerspectives,
                Set<Website> websites, Status status, TimeToBeat timeToBeat, Esrb esrb, Pegi pegi, External external, ImageInfo cover) {
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


}