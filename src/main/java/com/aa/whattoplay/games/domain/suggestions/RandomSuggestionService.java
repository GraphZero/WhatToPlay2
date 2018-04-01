package com.aa.whattoplay.games.domain.suggestions;

import com.aa.ddd.common.annotations.DomainService;
import com.aa.whattoplay.games.infastructure.entities.accounts.User;
import com.aa.whattoplay.games.infastructure.entities.accounts.UserPersonalRating;

import java.util.List;

@DomainService
public class RandomSuggestionService implements ISuggestionService {

    @Override
    public RecommendedGames suggestGamesForUser(User user) {
        List<UserPersonalRating> userRatings = user.getRatedGames();
        return null;
    }
}
