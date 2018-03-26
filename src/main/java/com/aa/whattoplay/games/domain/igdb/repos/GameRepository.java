package com.aa.whattoplay.games.domain.igdb.repos;

import com.aa.whattoplay.games.infastructure.entities.igdb.GameEntity;

public interface GameRepository {
    GameEntity save(GameEntity gameEntity);
}
