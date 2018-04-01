package com.aa.whattoplay.games.domain.suggestions;

import com.aa.whattoplay.games.infastructure.entities.igdb.GameEntity;

import java.util.Collection;

public interface GameRepository {
    GameEntity getOneRandomGame();
    Collection<GameEntity> getSeveralRandomGames(long numberOfGames);
}
