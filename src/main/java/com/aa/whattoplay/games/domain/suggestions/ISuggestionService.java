package com.aa.whattoplay.games.domain.suggestions;

import com.aa.whattoplay.games.infastructure.entities.accounts.User;

public interface ISuggestionService {
    RecommendedGames suggestGamesForUser(User user);
}
