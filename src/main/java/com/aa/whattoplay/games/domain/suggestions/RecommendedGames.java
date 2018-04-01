package com.aa.whattoplay.games.domain.suggestions;

import com.aa.whattoplay.games.infastructure.entities.accounts.User;
import com.aa.whattoplay.games.infastructure.entities.igdb.GameEntity;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RecommendedGames {
    public User owningUser;
    public List<GameEntity> recommendedGames;
}
