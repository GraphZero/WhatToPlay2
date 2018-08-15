package com.aa.whattoplay.games.infastructure.repos;

import com.aa.whattoplay.games.infastructure.entities.accounts.UserPersonalRating;
import com.aa.whattoplay.games.infastructure.entities.igdb.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface UserRatingsRepository extends JpaRepository<UserPersonalRating, Long> {
    Set<UserPersonalRating> findByUserId(long userId);

    @Query("SELECT MAX(U.id) " +
            "FROM UserPersonalRating U " +
            "JOIN GameEntity G ON U.gameEntity = G.id " +
            "WHERE U.user.id = :userId " +
            "GROUP BY G.id " )
    List<Long> getLatestRatings(@Param("userId") long userId);

    @Query("SELECT G " +
            "FROM UserPersonalRating U " +
            "JOIN GameEntity G ON U.gameEntity = G.id " +
            "WHERE U.id IN :reportsIds ")
    List<GameEntity> getGamesForIds(@Param("reportsIds") List<Long> reportsIds);
}
