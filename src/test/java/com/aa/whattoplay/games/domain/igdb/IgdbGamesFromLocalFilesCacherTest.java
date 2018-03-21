package com.aa.whattoplay.games.domain.igdb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IgdbGamesFromLocalFilesCacherTest {

    private IgdbGamesFromLocalFilesCacher igdbGamesFromLocalFilesCacher;
    private GamesFromJsonFilesLoader gamesFromJsonFilesLoader;

    @BeforeEach
    void setIgdbGamesFromLocalFilesCacher(){
        gamesFromJsonFilesLoader = new GamesFromJsonFilesLoader();
        igdbGamesFromLocalFilesCacher = new IgdbGamesFromLocalFilesCacher(gamesFromJsonFilesLoader);
    }

    @Test
    void persistGamesFromFiles() {
        // given
        // when
        // then
        igdbGamesFromLocalFilesCacher.persistGamesFromFiles();
    }
}