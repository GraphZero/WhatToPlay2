package com.aa.whattoplay.games.domain.igdb;

import com.aa.whattoplay.games.domain.igdb.json.GameJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

class JsonGameCacherTest {
    private JsonGameFileCacher jsonGameFileCacher;

    @BeforeEach
    void setJsonGameSaverService(){
        jsonGameFileCacher = new JsonGameFileCacher();
    }

    @Test
    void shouldSaveGames() {
        // given
        ArrayList<GameJson> gameJsons = new ArrayList<>();
        gameJsons.add(new GameJson(
                5, "a", "b", "c", "d", "f", 6, 5.5,
                6.6, 4, 5.5, 3, 7.7,
                9, 2, 9, LocalDate.now(), LocalDate.now(),
                LocalDate.now(), null, null, null, null ,null
        ));
        // when
        // then
        jsonGameFileCacher.saveGames(gameJsons);
    }
}