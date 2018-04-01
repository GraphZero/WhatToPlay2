package com.aa.whattoplay.games.application;

import com.aa.ddd.common.annotations.ApplicationService;
import com.aa.ddd.common.domain.IGenericCrudDao;
import com.aa.whattoplay.games.domain.igdb.JsonFilesManager;
import com.aa.whattoplay.games.domain.igdb.JsonGamesPersistenceService;
import com.aa.whattoplay.games.domain.igdb.json.CollectionJson;
import com.aa.whattoplay.games.domain.igdb.json.DeveloperJson;
import com.aa.whattoplay.games.domain.igdb.json.FranchiseJson;
import com.aa.whattoplay.games.domain.igdb.json.GameJson;
import com.aa.whattoplay.games.infastructure.entities.igdb.CollectionEntity;
import com.aa.whattoplay.games.infastructure.entities.igdb.DeveloperEntity;
import com.aa.whattoplay.games.infastructure.entities.igdb.FranchiseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    private final IGenericCrudDao<CollectionEntity> collectionsEntityCrudDao;
    private final IGenericCrudDao<FranchiseEntity> franchisesEntityCrudDao;
    private final IGenericCrudDao<DeveloperEntity> developerEntityCrudDao;

    public void persistCollectionsFromDefaultJsonFiles(){
        collectionsEntityCrudDao.setClazz(CollectionEntity.class);
        List<CollectionJson> collectionJsons = (List<CollectionJson>) jsonFilesManager.getAllObjectsFromJsonsFiles("./collections/", CollectionJson.class);
        collectionJsons.forEach( collectionJson ->
                collectionsEntityCrudDao.save(collectionJson.entity())
        );
    }

    public void persistFranchisesFromDefaultJsonFiles(){
        franchisesEntityCrudDao.setClazz(FranchiseEntity.class);
        List<FranchiseJson> franchisesJsons = (List<FranchiseJson>) jsonFilesManager.getAllObjectsFromJsonsFiles("./franchises/", FranchiseJson.class);
        franchisesJsons.forEach( franchiseJson ->
                franchisesEntityCrudDao.save(franchiseJson.entity())
        );
    }

    public void persistDevelopersFromDefaultJsonFiles(){
        developerEntityCrudDao.setClazz(DeveloperEntity.class);
        List<DeveloperJson> developerJsons = (List<DeveloperJson>) jsonFilesManager.getAllObjectsFromJsonsFiles("./developerEntities/", DeveloperJson.class);
        developerJsons.forEach( developerJson ->
                developerEntityCrudDao.save(developerJson.entity())
        );
    }

    public void persistGamesFromDefaultJsonFiles(){
        List<GameJson> gamesJsons = (List<GameJson>) jsonFilesManager.getAllObjectsFromJsonsFiles("./games/", GameJson.class);
        HashMap<String, Integer> numberOFInconsistentGames = jsonGamesPersistenceService.persistGameJsons(gamesJsons);
        jsonFilesManager.saveJsonToCustomPath(new ArrayList<>(numberOFInconsistentGames.values()), "D:\\Errors");
        log.info("Found " + numberOFInconsistentGames + " GameEntity objects which are inconsistent, may have default value");
    }

}
