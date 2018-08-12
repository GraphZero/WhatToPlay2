package com.aa.whattoplay.games.domain.suggestions.implementation;

import com.aa.ddd.common.annotations.DomainService;
import com.aa.whattoplay.games.domain.suggestions.GameRepository;
import com.aa.whattoplay.games.domain.suggestions.SuggestionService;
import com.aa.whattoplay.games.domain.suggestions.RecommendedGames;
import com.aa.whattoplay.games.domain.suggestions.GameEvaluation;
import com.aa.whattoplay.games.infastructure.entities.accounts.User;
import com.aa.whattoplay.games.infastructure.entities.igdb.GameEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@DomainService
@RequiredArgsConstructor
@Primary
public class RandomSuggestionService implements SuggestionService {
    private final GameRepository gameRepository;

    @Override
    public RecommendedGames suggestGamesForUser(User user) {
        List<GameEvaluation> randomGameEvaluations = new ArrayList<>(gameRepository.getSeveralRandomGames(10))
                .stream()
                .map(GameEntity::value)
                .collect(Collectors.toList());
        return new RecommendedGames(user.getId(), randomGameEvaluations);
    }


}
