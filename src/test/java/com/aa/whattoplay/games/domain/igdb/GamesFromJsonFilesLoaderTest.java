package com.aa.whattoplay.games.domain.igdb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GamesFromJsonFilesLoaderTest {
    private GamesFromJsonFilesLoader gamesFromJsonFilesLoader;

    @Test
    void getGamesFromFiles() {
        // given
        gamesFromJsonFilesLoader = new GamesFromJsonFilesLoader();
        // when
        // then
        assertEquals(1000, gamesFromJsonFilesLoader.getGamesFromFile(1).size());
    }
}