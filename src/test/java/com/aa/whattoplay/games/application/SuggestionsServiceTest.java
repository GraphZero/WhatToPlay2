package com.aa.whattoplay.games.application;

import com.aa.whattoplay.accounts.infastructure.entities.AccountEntity;
import com.aa.whattoplay.games.TestDataGenerator;
import com.aa.whattoplay.games.application.commands.AddUserRating;
import com.aa.whattoplay.games.domain.suggestions.UserRating;
import com.aa.whattoplay.games.infastructure.entities.accounts.UserPersonalRating;
import com.aa.whattoplay.games.infastructure.entities.igdb.GameEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
class SuggestionsServiceTest {

    @Autowired
    private SuggestionsService suggestionsService;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void queryRecommendedGamesForUser() {
        // given
        // when
        // then
    }

    @Test
    void shouldAddUserRating() {
        // given
        AccountEntity accountEntity = new AccountEntity("test", "test", "test", "testtest", "test@b", true);
        GameEntity gameEntity = TestDataGenerator.getTestGameEntity();
        testEntityManager.persist(accountEntity);
        testEntityManager.persist(gameEntity);
        // when
        Optional<UserPersonalRating> userPersonalRating
                = suggestionsService.addUserRating(new AddUserRating(accountEntity.getId(), gameEntity.getId(), UserRating.INTERESTED));
        // then
        userPersonalRating.ifPresent(
                userPersonalRating1 ->
                        assertEquals(accountEntity.getId(), userPersonalRating1.getUser().getId())
        );
        if (!userPersonalRating.isPresent()) fail("Couldnt persist Personal Ratings");
    }
}