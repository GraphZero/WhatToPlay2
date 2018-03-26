package com.aa.whattoplay.games.infastructure.entities;

import com.aa.whattoplay.games.domain.igdb.value.Status;
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

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestEntityManager
@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
class GameEntityTest {

    @Autowired
    private TestEntityManager testEntityManager;

    private static GameEntity getTestGameEntity(){
        return new GameEntity( "nam", "a", "b", "c", "d", 7, 6, 5,
                6, 4, 7, 3, 7,
                 LocalDate.now(), LocalDate.now(), LocalDate.now(), null, null,null,
                 null, null, null, null, Status.RELEASED,  null, null, null, null ,null);
    }

    private static Set<Developer> getTestDevelopers(){
        Set<Developer> developers;
        Developer developer = new Developer("a", "b", "c", "d",
                LocalDate.now(), "e", "f", 5, 10);
        Developer developer1 = new Developer("b", "b", "c", "d",
                LocalDate.now(), "e", "f", 5, 10);
        Developer developer2 = new Developer("c", "b", "c", "d",
                LocalDate.now(), "e", "f", 5, 10);
        developers = new HashSet<>( Arrays.asList( developer, developer1, developer2 ));
        return developers;
    }

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
        Set<Developer> developers = getTestDevelopers();
        developers.forEach(testEntityManager::persist);
        gameEntity.setDevelopers(developers);
        // when
        gameEntity = testEntityManager.persist(gameEntity);
        GameEntity gameEntity1 = testEntityManager.find(GameEntity.class, gameEntity.getId());
        // then
        assertEquals( 3, gameEntity1.getDevelopers().size());
    }

}