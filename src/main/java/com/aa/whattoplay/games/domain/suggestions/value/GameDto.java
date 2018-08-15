package com.aa.whattoplay.games.domain.suggestions.value;

import com.aa.whattoplay.games.domain.igdb.value.External;
import com.aa.whattoplay.games.domain.igdb.value.Status;
import com.aa.whattoplay.games.infastructure.entities.embeddables.Esrb;
import com.aa.whattoplay.games.infastructure.entities.embeddables.ImageInfo;
import com.aa.whattoplay.games.infastructure.entities.embeddables.Pegi;
import com.aa.whattoplay.games.infastructure.entities.embeddables.TimeToBeat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Getter
public class GameDto {
    private long id;
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
    private Collection collection;
    private Franchise franchise;
    private Set<Developer> developers;
    private Set<GameMode> gameModes;
    private Set<Genre> genres;
    private Set<PlayerPerspective> playerPerspectives;
    private Set<Website> websites;
    private Status status;
    private TimeToBeat timeToBeat;
    private Esrb esrb;
    private Pegi pegi;
    private External external;
    private ImageInfo cover;
}
