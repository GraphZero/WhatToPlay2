package com.aa.whattoplay.games.domain.suggestions.implementation;

import com.aa.ddd.common.annotations.DomainService;
import com.aa.whattoplay.games.domain.suggestions.ISuggestionService;
import com.aa.whattoplay.games.domain.suggestions.RecommendedGames;
import com.aa.whattoplay.games.infastructure.entities.accounts.User;

@DomainService
public class DecisionTreeSuggestionService implements ISuggestionService {

    @Override
    public RecommendedGames suggestGamesForUser(User user) {
        return null;
    }
}
