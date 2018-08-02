package com.aa.whattoplay.games.domain.suggestions;

import com.aa.whattoplay.games.domain.suggestions.value.Game;
import com.aa.whattoplay.games.domain.suggestions.value.Genre;
import com.aa.whattoplay.games.infastructure.entities.accounts.User;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class RecommendedGames {
    public long owningUserId;
    public List<Game> recommendedGames;

    public Map<Genre, Long> getGenreOccurences() {
        return recommendedGames
                .stream()
                .flatMap(x -> x.getGenre().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

}
