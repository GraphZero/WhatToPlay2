package com.aa.whattoplay.games.domain.suggestions;

import com.aa.whattoplay.games.domain.suggestions.value.GameDto;
import com.aa.whattoplay.games.domain.suggestions.value.Genre;
import com.aa.whattoplay.games.infastructure.entities.igdb.GameEntity;
import com.aa.whattoplay.games.infastructure.entities.igdb.GenreEntity;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
public class RecommendedGames {
    public long owningUserId;
    public List<GameDto> games;

    public Map<Genre, Long> getGenreOccurences() {
        return games
                .stream()
                .flatMap(x -> x.getGenres().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

}
