package com.aa.whattoplay.games.domain.suggestions;

import com.aa.ddd.common.annotations.DomainService;
import com.aa.whattoplay.games.infastructure.entities.accounts.User;
import com.aa.whattoplay.games.infastructure.entities.igdb.GameEntity;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@DomainService
@RequiredArgsConstructor
public class RandomSuggestionService implements ISuggestionService {
    private final GameRepository gameRepository;

    @Override
    public RecommendedGames suggestGamesForUser(User user) {
        List<GameEntity> randomGameEntities = new ArrayList<>(gameRepository.getSeveralRandomGames(10));
        return new RecommendedGames(user, randomGameEntities);
    }


}
