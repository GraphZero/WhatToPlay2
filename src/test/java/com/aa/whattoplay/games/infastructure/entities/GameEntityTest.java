package com.aa.whattoplay.games.infastructure.entities;

import com.aa.whattoplay.games.infastructure.entities.igdb.DeveloperEntity;
import com.aa.whattoplay.games.infastructure.entities.igdb.FranchiseEntity;
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
        FranchiseEntity franchiseEntity = testEntityManager.persist(new FranchiseEntity("a", "b", LocalDate.now(), LocalDate.now()));
        GameEntity gameEntity = getTestGameEntity();
        gameEntity.setFranchiseEntity(franchiseEntity);
        // when
        gameEntity = testEntityManager.persist(gameEntity);
        GameEntity gameEntity1 = testEntityManager.find(GameEntity.class, gameEntity.getId());
        // then
        assertEquals( "a", gameEntity1.getFranchiseEntity().getName());
    }

    @Test
    void shouldLinkDevelopersWithGames() {
        // given
        GameEntity gameEntity = getTestGameEntity();
        Set<DeveloperEntity> developerEntities = getTestDevelopersEntities();
        developerEntities.forEach(testEntityManager::persist);
        gameEntity.setDeveloperEntities(developerEntities);
        // when
        gameEntity = testEntityManager.persist(gameEntity);
        GameEntity gameEntity1 = testEntityManager.find(GameEntity.class, gameEntity.getId());
        // then
        assertEquals( 3, gameEntity1.getDeveloperEntities().size());
    }

}