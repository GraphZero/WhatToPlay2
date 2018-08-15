package com.aa.whattoplay.games.infastructure.repos;

import com.aa.whattoplay.games.domain.suggestions.GameRepository;
import com.aa.whattoplay.games.infastructure.entities.igdb.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/**
 * Native queries will probably only work with MsSql dialect.
 */
public interface SpringDataGamesRepository extends GameRepository, JpaRepository<GameEntity, Long> {

    @Override
    @Query(value = "SELECT TOP 1 * FROM Games ORDER BY NEWID()", nativeQuery = true)
    GameEntity getOneRandomGame();

    @Override
    @Query(value = "SELECT TOP (:limit) * FROM Games ORDER BY NEWID()", nativeQuery = true)
    Collection<GameEntity> getSeveralRandomGames(@Param("limit") long numberOfGames);

    @Override
    Set<GameEntity> getByNameStartingWith(String name);
}
