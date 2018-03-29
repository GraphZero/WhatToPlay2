package com.aa.whattoplay.games.domain.igdb;

import com.aa.whattoplay.games.domain.igdb.json.GameJson;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static com.aa.whattoplay.games.TestDataGenerator.getOneThousandTestJsonFranchises;
import static com.aa.whattoplay.games.TestDataGenerator.getTestJsonFranchises;
import static com.aa.whattoplay.games.TestDataGenerator.getTestJsonGamesWithoutListsOfIds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class JsonFilesManagerTest {
    private JsonFilesManager jsonFilesManager;

    @Test
    @Disabled
    void shouldSaveGamesToDefaultPath() {
        // given
        jsonFilesManager = new JsonFilesManager();

        // when
        // then
        jsonFilesManager.saveJsonToDefaultPath(getTestJsonGamesWithoutListsOfIds());
        assertTrue(new File("./Games/GamesPart1.json").exists());
    }

    @Test
    void shouldSaveGamesToCustomPath() {
        // given
        jsonFilesManager = new JsonFilesManager();
        // when
        // then
        jsonFilesManager.saveJsonToCustomPath(getTestJsonGamesWithoutListsOfIds(), "D:\\test\\");
        assertTrue(new File("D:\\test\\GamesPart1.json").exists());
    }

    @Test
    void shouldLoadGenericObjects() {
        // given
        jsonFilesManager = new JsonFilesManager();
        // when
        // then
        jsonFilesManager.saveJsonToCustomPath(getTestJsonGamesWithoutListsOfIds(), "D:\\test\\");
        List<GameJson> gameJsons = (List<GameJson> ) jsonFilesManager.getAllObjectsFromJsonsFiles("D:\\test\\", GameJson.class);
        assertEquals(3, gameJsons.size());
    }

    @Test
    @Disabled
    void shouldSaveFranchisesToDefaultPath() {
        // given
        jsonFilesManager = new JsonFilesManager();
        // when
        // then
        jsonFilesManager.saveJsonToDefaultPath(getTestJsonFranchises());
        assertTrue(new File("./Franchises/FranchisesPart1.json").exists());
    }

    @Test
    @Disabled
    void shouldSave1002FranchisesToDefaultPath() {
        // given
        jsonFilesManager = new JsonFilesManager();
        // when
        // then
        jsonFilesManager.saveJsonToDefaultPath(getOneThousandTestJsonFranchises());
        assertTrue(new File("./Franchises/FranchisesPart1.json").exists() && new File("./Franchises/FranchisesPart2.json").exists());
    }



}