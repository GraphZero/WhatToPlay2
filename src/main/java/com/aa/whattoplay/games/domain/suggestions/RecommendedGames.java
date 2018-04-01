package com.aa.whattoplay.games.domain.suggestions;

import com.aa.whattoplay.games.domain.suggestions.value.Game;
import com.aa.whattoplay.games.infastructure.entities.accounts.User;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RecommendedGames {
    public User owningUser;
    public List<Game> recommendedGames;
}
