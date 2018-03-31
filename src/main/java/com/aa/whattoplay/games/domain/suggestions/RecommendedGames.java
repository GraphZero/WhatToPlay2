package com.aa.whattoplay.games.domain.suggestions;

import com.aa.whattoplay.games.infastructure.entities.accounts.User;

import java.util.List;

public class RecommendedGames {
    public User owningUser;
    public List<Game> recommendedGames;
    public List<Double> gamesCorrectness;
}
