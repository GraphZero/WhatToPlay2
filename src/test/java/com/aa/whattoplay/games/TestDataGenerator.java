package com.aa.whattoplay.games;

import com.aa.whattoplay.games.domain.igdb.json.DeveloperJson;
import com.aa.whattoplay.games.domain.igdb.json.FranchiseJson;
import com.aa.whattoplay.games.domain.igdb.json.GameJson;
import com.aa.whattoplay.games.domain.igdb.value.Status;
import com.aa.whattoplay.games.infastructure.entities.igdb.DeveloperEntity;
import com.aa.whattoplay.games.infastructure.entities.igdb.GameEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestDataGenerator {

    public static ArrayList<GameJson> getTestJsonGamesWithoutListsOfIds(){
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
        gameJsons.add(new GameJson(
                7, "a", "b", "c", "d", "f", 6, 5.5,
                6.6, 4, 5.5, 3, 7.7,
                9, 2, 9, LocalDate.now(), LocalDate.now(),
                LocalDate.now(), null, null, null, null ,null
        ));
        return gameJsons;
    }

    public static ArrayList<DeveloperJson> getTestDevelopersJsons(){
        ArrayList<DeveloperJson> developers;
        DeveloperJson developer = new DeveloperJson(1, "a", "b", "c", "d",
                LocalDate.now(), "e", "f", 5, 10);
        DeveloperJson developer1 = new DeveloperJson(2 ,"b", "b", "c", "d",
                LocalDate.now(), "e", "f", 5, 10);
        developers = new ArrayList<>( Arrays.asList( developer, developer1));
        return developers;
    }

    public static ArrayList<FranchiseJson> getTestJsonFranchises(){
        ArrayList<FranchiseJson> franchiseJsons = new ArrayList<>();
        franchiseJsons.add(new FranchiseJson(3, "a", "b", LocalDate.now(), LocalDate.now()));
        franchiseJsons.add(new FranchiseJson(4, "a", "b", LocalDate.now(), LocalDate.now()));
        return franchiseJsons;
    }

    public static ArrayList<FranchiseJson> getOneThousandTestJsonFranchises(){
        ArrayList<FranchiseJson> franchiseJsons = new ArrayList<>();
        for (int i = 0; i < 1002; i++) {
            franchiseJsons.add(new FranchiseJson(i, "a", "b", LocalDate.now(), LocalDate.now()));
        }
        return franchiseJsons;
    }

    public static GameEntity getTestGameEntity(){
        return new GameEntity( "nam", "a", "b", "c", "d", 7, 6, 5,
                6, 4, 7, 3, 7,
                LocalDate.now(), LocalDate.now(), LocalDate.now(), null, null,null,
                null, null, null, null, Status.RELEASED,  null, null, null, null ,null);
    }

    public static Set<DeveloperEntity> getTestDevelopersEntities(){
        Set<DeveloperEntity> developerEntities;
        DeveloperEntity developerEntity = new DeveloperEntity(1,"a", "b", "c", "d",
                LocalDate.now(), "e", "f", 5, 10);
        DeveloperEntity developerEntity1 = new DeveloperEntity(2,"b", "b", "c", "d",
                LocalDate.now(), "e", "f", 5, 10);
        DeveloperEntity developerEntity2 = new DeveloperEntity(3,"c", "b", "c", "d",
                LocalDate.now(), "e", "f", 5, 10);
        developerEntities = new HashSet<>( Arrays.asList(developerEntity, developerEntity1, developerEntity2));
        return developerEntities;
    }
}
