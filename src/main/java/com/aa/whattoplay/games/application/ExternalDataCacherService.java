package com.aa.whattoplay.games.application;

import com.aa.ddd.common.annotations.ApplicationService;
import com.aa.ddd.common.domain.IGenericCrudDao;
import com.aa.whattoplay.games.domain.igdb.JsonFilesManager;
import com.aa.whattoplay.games.domain.igdb.JsonGamesPersistenceService;
import com.aa.whattoplay.games.domain.igdb.json.CollectionJson;
import com.aa.whattoplay.games.domain.igdb.json.DeveloperJson;
import com.aa.whattoplay.games.domain.igdb.json.FranchiseJson;
import com.aa.whattoplay.games.domain.igdb.json.GameJson;
import com.aa.whattoplay.games.infastructure.entities.igdb.Collection;
import com.aa.whattoplay.games.infastructure.entities.igdb.Developer;
import com.aa.whattoplay.games.infastructure.entities.igdb.Franchise;
import com.aa.whattoplay.games.infastructure.entities.igdb.Game;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class loads objects from JSON files and then persist them in relational database.
 */

@ApplicationService
@RequiredArgsConstructor
@Transactional
@SuppressWarnings(value = "unchecked")
@Slf4j
public class ExternalDataCacherService {
    private final JsonFilesManager jsonFilesManager;
    private final JsonGamesPersistenceService jsonGamesPersistenceService;
    private final IGenericCrudDao<Collection> collectionsEntityCrudDao;
    private final IGenericCrudDao<Franchise> franchisesEntityCrudDao;
    private final IGenericCrudDao<Developer> developerEntityCrudDao;

    public void persistCollectionsFromDefaultJsonFiles(){
        collectionsEntityCrudDao.setClazz(Collection.class);
        List<CollectionJson> collectionJsons = (List<CollectionJson>) jsonFilesManager.getAllObjectsFromJsonsFiles("./collections/", CollectionJson.class);
        collectionJsons.forEach( collectionJson ->
                collectionsEntityCrudDao.save(collectionJson.entity())
        );
    }

    public void persistFranchisesFromDefaultJsonFiles(){
        franchisesEntityCrudDao.setClazz(Franchise.class);
        List<FranchiseJson> franchisesJsons = (List<FranchiseJson>) jsonFilesManager.getAllObjectsFromJsonsFiles("./franchises/", FranchiseJson.class);
        franchisesJsons.forEach( franchiseJson ->
                franchisesEntityCrudDao.save(franchiseJson.entity())
        );
    }

    public void persistDevelopersFromDefaultJsonFiles(){
        developerEntityCrudDao.setClazz(Developer.class);
        List<DeveloperJson> developerJsons = (List<DeveloperJson>) jsonFilesManager.getAllObjectsFromJsonsFiles("./developers/", DeveloperJson.class);
        developerJsons.forEach( developerJson ->
                developerEntityCrudDao.save(developerJson.entity())
        );
    }

    public void persistGamesFromDefaultJsonFiles(){
        List<GameJson> gamesJsons = (List<GameJson>) jsonFilesManager.getAllObjectsFromJsonsFiles("./games/", GameJson.class);
        HashMap<String, Integer> numberOFInconsistentGames = jsonGamesPersistenceService.persistGameJsons(gamesJsons);
        jsonFilesManager.saveJsonToCustomPath(new ArrayList<>(numberOFInconsistentGames.values()), "D:\\Errors");
        log.info("Found " + numberOFInconsistentGames + " Game objects which are inconsistent, may have default value");
    }

}
