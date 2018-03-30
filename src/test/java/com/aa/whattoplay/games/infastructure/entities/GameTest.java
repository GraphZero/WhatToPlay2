package com.aa.whattoplay.games.infastructure.entities;

import com.aa.whattoplay.games.infastructure.entities.igdb.Developer;
import com.aa.whattoplay.games.infastructure.entities.igdb.Franchise;
import com.aa.whattoplay.games.infastructure.entities.igdb.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

import static com.aa.whattoplay.games.TestDataGenerator.getTestDevelopersEntities;
import static com.aa.whattoplay.games.TestDataGenerator.getTestGameEntity;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestEntityManager
@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
class GameTest {

    @Autowired
    private TestEntityManager testEntityManager;



    @Test
    void shouldLinkGameEntityAndFranchises() {
        // given
        Franchise franchise = testEntityManager.persist(new Franchise("a", "b", LocalDate.now(), LocalDate.now()));
        Game game = getTestGameEntity();
        game.setFranchise(franchise);
        // when
        game = testEntityManager.persist(game);
        Game game1 = testEntityManager.find(Game.class, game.getId());
        // then
        assertEquals( "a", game1.getFranchise().getName());
    }

    @Test
    void shouldLinkDevelopersWithGames() {
        // given
        Game game = getTestGameEntity();
        Set<Developer> developers = getTestDevelopersEntities();
        developers.forEach(testEntityManager::persist);
        game.setDevelopers(developers);
        // when
        game = testEntityManager.persist(game);
        Game game1 = testEntityManager.find(Game.class, game.getId());
        // then
        assertEquals( 3, game1.getDevelopers().size());
    }

}