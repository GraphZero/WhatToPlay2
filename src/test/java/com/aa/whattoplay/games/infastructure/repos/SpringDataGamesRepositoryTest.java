package com.aa.whattoplay.games.infastructure.repos;

import com.aa.whattoplay.games.infastructure.entities.igdb.GameEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
class SpringDataGamesRepositoryTest {

    @Autowired
    private SpringDataGamesRepository springDataGamesRepository;

    @Test
    void getOneRandomGame() {
        // given
        // when
        // then
        GameEntity gameEntity = springDataGamesRepository.getOneRandomGame();
        assertNotNull(gameEntity);
    }

    @Test
    void getSeveralRandomGames() {
        // given
        // when
        // then
        Iterable<GameEntity> randomGames = springDataGamesRepository.getSeveralRandomGames(10);
        assertEquals(10, ((Collection<GameEntity>) randomGames).size());
    }
}