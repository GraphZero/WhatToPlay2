package com.aa.whattoplay.games.domain.suggestions.implementation;

import com.aa.whattoplay.games.domain.suggestions.RecommendedGames;
import com.aa.whattoplay.games.infastructure.entities.accounts.User;

public interface SuggestionService {
    RecommendedGames suggestGamesForUser(User user);
}
