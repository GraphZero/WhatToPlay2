package com.aa.whattoplay.games.domain.suggestions.implementation;

import com.aa.whattoplay.games.infastructure.entities.igdb.GameEntity;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface GameRepository {
    GameEntity getOneRandomGame();
    Collection<GameEntity> getSeveralRandomGames(long numberOfGames);
    Set<GameEntity> getByNameStartingWith(String name);
    Optional<GameEntity> findById(long id);
}
