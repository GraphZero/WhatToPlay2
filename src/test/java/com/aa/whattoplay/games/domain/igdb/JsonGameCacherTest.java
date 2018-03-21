package com.aa.whattoplay.games.domain.igdb;

import com.aa.whattoplay.games.domain.igdb.json.FranchiseJson;
import com.aa.whattoplay.games.domain.igdb.json.GameJson;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;


class JsonGameCacherTest {
    private JsonFilesManager jsonFilesManager;

    @Test
    @Disabled
    void shouldSaveGamesToDefaultPath() {
        // given
        jsonFilesManager = new JsonFilesManager();

        // when
        // then
        jsonFilesManager.saveJsonToDefaultPath(getTestGames());
        assertTrue(new File("./Games/GamesPart1.json").exists());
    }

    @Test
    void shouldSaveGamesToCustomPath() {
        // given
        jsonFilesManager = new JsonFilesManager();
        // when
        // then
        jsonFilesManager.saveJsonToCustomPath(getTestGames(), "D:\\");
        assertTrue(new File("D:\\GamesPart1.json").exists());
    }

    @Test
    @Disabled
    void shouldSaveFranchisesToDefaultPath() {
        // given
        jsonFilesManager = new JsonFilesManager();
        // when
        // then
        jsonFilesManager.saveJsonToDefaultPath(getTestFranchises());
        assertTrue(new File("./Franchises/FranchisesPart1.json").exists());
    }

    @Test
    @Disabled
    void shouldSave1002FranchisesToDefaultPath() {
        // given
        jsonFilesManager = new JsonFilesManager();
        // when
        // then
        jsonFilesManager.saveJsonToDefaultPath(get1002TestFranchises());
        assertTrue(new File("./Franchises/FranchisesPart1.json").exists() && new File("./Franchises/FranchisesPart2.json").exists());
    }

    private ArrayList<GameJson> getTestGames(){
        ArrayList<GameJson> gameJsons = new ArrayList<>();
        gameJsons.add(new GameJson(
                5, "a", "b", "c", "d", "f", 6, 5.5,
                6.6, 4, 5.5, 3, 7.7,
                9, 2, 9, LocalDate.now(), LocalDate.now(),
                LocalDate.now(), null, null, null, null ,null
        ));
        gameJsons.add(new GameJson(
                6, "a", "b", "c", "d", "f", 6, 5.5,
                6.6, 4, 5.5, 3, 7.7,
                9, 2, 9, LocalDate.now(), LocalDate.now(),
                LocalDate.now(), null, null, null, null ,null
        ));
        return gameJsons;
    }

    private ArrayList<FranchiseJson> getTestFranchises(){
        ArrayList<FranchiseJson> franchiseJsons = new ArrayList<>();
        franchiseJsons.add(new FranchiseJson(3, "a", "b", LocalDate.now(), LocalDate.now()));
        franchiseJsons.add(new FranchiseJson(4, "a", "b", LocalDate.now(), LocalDate.now()));
        return franchiseJsons;
    }

    private ArrayList<FranchiseJson> get1002TestFranchises(){
        ArrayList<FranchiseJson> franchiseJsons = new ArrayList<>();
        for (int i = 0; i < 1002; i++) {
            franchiseJsons.add(new FranchiseJson(i, "a", "b", LocalDate.now(), LocalDate.now()));
        }
        return franchiseJsons;
    }

}