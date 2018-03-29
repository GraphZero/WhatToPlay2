package com.aa.whattoplay.games.infastructure.entities;

import com.aa.whattoplay.games.infastructure.entities.igdb.Developer;
import com.aa.whattoplay.games.infastructure.entities.igdb.Franchise;
import com.aa.whattoplay.games.infastructure.entities.igdb.GameEntity;
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
class GameEntityTest {

    @Autowired
    private TestEntityManager testEntityManager;



    @Test
    void shouldLinkGameEntityAndFranchises() {
        // given
        Franchise franchise = testEntityManager.persist(new Franchise("a", "b", LocalDate.now(), LocalDate.now()));
        GameEntity gameEntity = getTestGameEntity();
        gameEntity.setFranchise(franchise);
        // when
        gameEntity = testEntityManager.persist(gameEntity);
        GameEntity gameEntity1 = testEntityManager.find(GameEntity.class, gameEntity.getId());
        // then
        assertEquals( "a", gameEntity1.getFranchise().getName());
    }

    @Test
    void shouldLinkDevelopersWithGames() {
        // given
        GameEntity gameEntity = getTestGameEntity();
        Set<Developer> developers = getTestDevelopersEntities();
        developers.forEach(testEntityManager::persist);
        gameEntity.setDevelopers(developers);
        // when
        gameEntity = testEntityManager.persist(gameEntity);
        GameEntity gameEntity1 = testEntityManager.find(GameEntity.class, gameEntity.getId());
        // then
        assertEquals( 3, gameEntity1.getDevelopers().size());
    }

}