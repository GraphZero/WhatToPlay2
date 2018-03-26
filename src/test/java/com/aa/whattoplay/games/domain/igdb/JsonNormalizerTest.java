package com.aa.whattoplay.games.domain.igdb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
class JsonNormalizerTest {
    @Autowired
    private JsonNormalizer jsonNormalizer;

    @Test
    void persistGamesFromFiles() {
        // given
        // when
        // then
        //jsonNormalizer.persistGamesFromFiles();
    }
}