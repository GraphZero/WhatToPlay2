package com.aa.whattoplay.games.domain.igdb;

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

    @BeforeEach
    void setIgdbGamesInfoRequester(){
        LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(Logger.ROOT_LOGGER_NAME, LogLevel.INFO);
        igdbJsonRequester = new IgdbJsonRequester();
    }

    @Test
    @Disabled
    void getSomeGamesFromIgdb() {
        // given
        // when
        List<GameJson> games = igdbJsonRequester.getSomeGamesFromIgdb(19);
        // then
        assertEquals(1000, games.size());
    }

    @Test
    void getFranchisesFromIgdb() {
        // given
        // when
        // then
    }
}