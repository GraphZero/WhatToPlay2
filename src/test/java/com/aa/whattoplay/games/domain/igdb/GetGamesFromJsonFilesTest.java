package com.aa.whattoplay.games.domain.igdb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetGamesFromJsonFilesTest {
    private GetGamesFromJsonFiles getGamesFromJsonFiles;

    @Test
    void getGamesFromFiles() {
        // given
        getGamesFromJsonFiles = new GetGamesFromJsonFiles();
        // when
        // then
        assertEquals(1000, getGamesFromJsonFiles.getGamesFromFile(1).size());
    }
}