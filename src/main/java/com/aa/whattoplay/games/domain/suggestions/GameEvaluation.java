package com.aa.whattoplay.games.domain.suggestions;

import com.aa.whattoplay.games.domain.igdb.value.Status;
import com.aa.whattoplay.games.domain.suggestions.value.*;
import com.aa.whattoplay.games.infastructure.entities.embeddables.Esrb;
import com.aa.whattoplay.games.infastructure.entities.embeddables.Pegi;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Getter
public class GameEvaluation {
    @Setter
    private UserRating userRating;
    private double popularity;
    private double rating;
    private LocalDate firstReleaseDate;
    private Collection collection;
    private Franchise franchise;
    private Set<Developer> developer;
    private Set<GameMode> gameMode;
    private Set<Genre> genre;
    private Set<PlayerPerspective> playerPerspective;
    private Status status;
    private Esrb esrb;
    private Pegi pegi;



    public List<Object> getLearnableAttributes() {
        return  Arrays.asList(userRating, popularity, rating,
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
