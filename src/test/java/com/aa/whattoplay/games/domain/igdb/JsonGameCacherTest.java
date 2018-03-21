package com.aa.whattoplay.games.domain.igdb;

import com.aa.whattoplay.games.domain.igdb.json.FranchiseJson;
import com.aa.whattoplay.games.domain.igdb.json.GameJson;
import com.aa.whattoplay.games.infastructure.entities.Franchise;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

class JsonGameCacherTest {
    private JsonToFileCacherService jsonToFileCacherService;

    @Test
    void shouldSaveGamesToDefaultPath() {
        // given
        jsonToFileCacherService = new JsonToFileCacherService();

        // when
        // then
        jsonToFileCacherService.saveJsonToDefaultPath(getTestGames());
    }

    @Test
    void shouldSaveGamesToCustomPath() {
        // given
        jsonToFileCacherService = new JsonToFileCacherService();
        // when
        // then
        jsonToFileCacherService.saveJsonToCustomPath(getTestGames(), "D:\\");
    }

    @Test
    void shouldSaveFranchisesToDefaultPath() {
        // given
        jsonToFileCacherService = new JsonToFileCacherService();
        // when
        // then
        jsonToFileCacherService.saveJsonToDefaultPath(getTestFranchises());
    }

    @Test
    void shouldSave1002FranchisesToDefaultPath() {
        // given
        jsonToFileCacherService = new JsonToFileCacherService();
        // when
        // then
        jsonToFileCacherService.saveJsonToDefaultPath(get1002TestFranchises());
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