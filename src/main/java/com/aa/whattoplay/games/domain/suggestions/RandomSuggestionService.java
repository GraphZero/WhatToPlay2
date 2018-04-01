package com.aa.whattoplay.games.domain.suggestions;

import com.aa.ddd.common.annotations.DomainService;
import com.aa.whattoplay.games.domain.suggestions.value.Game;
import com.aa.whattoplay.games.infastructure.entities.accounts.User;
import com.aa.whattoplay.games.infastructure.entities.igdb.GameEntity;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@DomainService
@RequiredArgsConstructor
public class RandomSuggestionService implements ISuggestionService {
    private final GameRepository gameRepository;

    @Override
    public RecommendedGames suggestGamesForUser(User user) {
        List<Game> randomGames = new ArrayList<>(gameRepository.getSeveralRandomGames(10))
                .stream()
                .map(GameEntity::value)
                .collect(Collectors.toList());
        return new RecommendedGames(user, randomGames);
    }


}
