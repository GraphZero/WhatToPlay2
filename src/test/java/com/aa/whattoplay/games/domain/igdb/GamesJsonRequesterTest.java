package com.aa.whattoplay.games.domain.igdb;

import com.aa.whattoplay.games.domain.igdb.json.CollectionJson;
import com.aa.whattoplay.games.domain.igdb.json.DeveloperJson;
import com.aa.whattoplay.games.domain.igdb.json.FranchiseJson;
import com.aa.whattoplay.games.domain.igdb.json.GameJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GamesJsonRequesterTest {
    private IgdbJsonRequester igdbJsonRequester;
    private JsonToFileCacherService jsonToFileCacherService;


    @BeforeEach
    void setIgdbGamesInfoRequester(){
        LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(Logger.ROOT_LOGGER_NAME, LogLevel.INFO);
        igdbJsonRequester = new IgdbJsonRequester();
        jsonToFileCacherService = new JsonToFileCacherService();
    }

    /**
     * Dont use it too much since there is a limit on api requests.
     */

    @Test
    @Disabled
    void getSomeGamesFromIgdb() {
        // given
        // when
        List<GameJson> games = igdbJsonRequester.getSomeGamesFromIgdb(19);
        // then
        assertEquals(1000, games.size());
    }


}