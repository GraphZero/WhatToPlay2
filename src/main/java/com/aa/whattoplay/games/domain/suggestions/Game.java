package com.aa.whattoplay.games.domain.suggestions;

import com.aa.ddd.common.annotations.ValueObject;
import com.aa.whattoplay.games.domain.igdb.value.External;
import com.aa.whattoplay.games.domain.igdb.value.Status;
import com.aa.whattoplay.games.domain.suggestions.value.*;
import com.aa.whattoplay.games.infastructure.entities.embeddables.Esrb;
import com.aa.whattoplay.games.infastructure.entities.embeddables.ImageInfo;
import com.aa.whattoplay.games.infastructure.entities.embeddables.Pegi;
import com.aa.whattoplay.games.infastructure.entities.embeddables.TimeToBeat;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Value
@Builder
public class Game {
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
    private Set<Developer> developer;
    private Set<GameMode> gameMode;
    private Set<Genre> genre;
    private Set<PlayerPerspective> playerPerspective;
    private Set<Website> website;
    private Status status;
    private TimeToBeat timeToBeat;
    private Esrb esrb;
    private Pegi pegi;
    private External external;
    private ImageInfo cover;

    public List<Object> getLearnableAttributes() {
        return  Arrays.asList(
                developer.stream().map(dev -> dev.getName().trim()).collect(Collectors.toList()),
                gameMode.stream().map(dev -> dev.getName().trim()).collect(Collectors.toList()),
                genre.stream().map(dev -> dev.getName().trim()).collect(Collectors.toList()),
                playerPerspective.stream().map(dev -> dev.getName().trim()).collect(Collectors.toList()),
                firstReleaseDate.getYear(),
                collection.getName().trim(),
                franchise.getName().trim(),
                status.getGameStatus(),
                esrb.getEsrbRating(),
                pegi == null ? "EIGHTEEN" : pegi.getPegiRating() );
    }

}
