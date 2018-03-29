package com.aa.whattoplay.games.domain.igdb;

import com.aa.whattoplay.games.domain.igdb.json.GameJson;
import com.aa.whattoplay.games.infastructure.entities.igdb.GameEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static com.aa.whattoplay.games.TestDataGenerator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
class JsonGamesPersistenceServiceTest {

    @Autowired
    private JsonGamesPersistenceService jsonGamesPersistenceService;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void shouldSaveGameJson() {
        // given
        GameJson gameJson = getTestJsonGamesWithoutListsOfIds().get(0);
        testEntityManager.persist(getTestDevelopersJsons().get(0).entity());
        testEntityManager.persist(getTestDevelopersJsons().get(1).entity());
        testEntityManager.persist(getTestJsonFranchises().get(0).entity());
        gameJson.setDevelopersIds(Arrays.asList(getTestDevelopersJsons().get(0).getId(), getTestDevelopersJsons().get(1).getId()));
        gameJson.setFranchiseId(getTestJsonFranchises().get(0).getId());
        // when
        jsonGamesPersistenceService.persistGameJson(gameJson);
        // then
        GameEntity gameEntity = testEntityManager.find(GameEntity.class, getTestJsonGamesWithoutListsOfIds().get(0).getId());
        assertEquals( 2, gameEntity.getDevelopers().size() );
    }
}