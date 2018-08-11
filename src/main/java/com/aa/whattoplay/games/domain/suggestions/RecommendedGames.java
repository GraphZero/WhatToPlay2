package com.aa.whattoplay.games.domain.suggestions;

import com.aa.whattoplay.games.domain.suggestions.value.Genre;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
public class RecommendedGames {
    public long owningUserId;
    public List<GameEvaluation> recommendedGameEvaluations;

    public Map<Genre, Long> getGenreOccurences() {
        return recommendedGameEvaluations
                .stream()
                .flatMap(x -> x.getGenre().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

}
