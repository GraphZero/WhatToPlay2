package com.aa.whattoplay.games.application;

import com.aa.ddd.common.annotations.ApplicationService;
import com.aa.ddd.common.domain.IGenericCrudDao;
import com.aa.whattoplay.games.domain.igdb.JsonFilesManager;
import com.aa.whattoplay.games.domain.igdb.json.CollectionJson;
import com.aa.whattoplay.games.domain.igdb.json.FranchiseJson;
import com.aa.whattoplay.games.infastructure.entities.igdb.Collection;
import com.aa.whattoplay.games.infastructure.entities.igdb.Franchise;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class ExternalDataCacherService {
    private final JsonFilesManager jsonFilesManager;
    private final IGenericCrudDao<Collection> collectionsEntityCrudDao;
    private final IGenericCrudDao<Franchise> franchisesEntityCrudDao;

    public void cacheCollections(){
        collectionsEntityCrudDao.setClazz(Collection.class);
        List<CollectionJson> collectionJsons = (List<CollectionJson>) jsonFilesManager.getAllObjectsFromJsons("./collections/", CollectionJson.class);
        collectionJsons.forEach( collectionJson ->
                collectionsEntityCrudDao.save(collectionJson.entity())
        );
    }

    public void cacheFranchises(){
        franchisesEntityCrudDao.setClazz(Franchise.class);
        List<FranchiseJson> franchisesJson = (List<FranchiseJson>) jsonFilesManager.getAllObjectsFromJsons("./franchises/", FranchiseJson.class);
        franchisesJson.forEach( franchiseJson ->
                franchisesEntityCrudDao.save(franchiseJson.entity())
        );
    }


}
