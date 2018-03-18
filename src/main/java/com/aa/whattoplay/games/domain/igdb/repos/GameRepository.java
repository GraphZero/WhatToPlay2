package com.aa.whattoplay.games.domain.igdb.repos;

import com.aa.whattoplay.games.infastructure.entities.GameEntity;

public interface GameRepository {
    GameEntity save(GameEntity gameEntity);
}
