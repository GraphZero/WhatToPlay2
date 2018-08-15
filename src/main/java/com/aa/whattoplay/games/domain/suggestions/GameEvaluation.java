package com.aa.whattoplay.games.domain.suggestions;

import com.aa.whattoplay.games.domain.igdb.value.Status;
import com.aa.whattoplay.games.domain.suggestions.value.*;
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
    private Pegi pegi;

    public List<Object> getLearnableAttributes() {
        return  Arrays.asList(userRating, popularity,
                rating == 0.0 ? null : rating,
                getCollectionOrEmptyString(developer.stream().map(dev -> dev.getName().trim()).collect(Collectors.toList())),
                getCollectionOrEmptyString(gameMode.stream().map(dev -> dev.getName().trim()).collect(Collectors.toList())),
                getCollectionOrEmptyString(genre.stream().map(dev -> dev.getName().trim()).collect(Collectors.toList())),
                getCollectionOrEmptyString(playerPerspective.stream().map(dev -> dev.getName().trim()).collect(Collectors.toList())),
                firstReleaseDate.getYear(),
                collection.getName().replaceAll(",", "").replaceAll(":", "").trim().equals("UNKNOWN COLLECTION") ? "" : collection.getName().trim(),
                franchise.getName().replaceAll(",", "").replaceAll(":", "").trim().equals("UNKNOWN FRANCHISE") ? "" : collection.getName().trim(),
                status,
                pegi == null ? "" : pegi.getPegiRating() );
    }

    private Object getCollectionOrEmptyString(List<Object> collectionToCollect){
        if ( collectionToCollect.size() > 0 ){
            return collectionToCollect;
        } else{
            return "";
        }
    }

}
