package com.aa.whattoplay.games.application;

import com.aa.ddd.common.annotations.ApplicationService;
import com.aa.ddd.common.domain.IGenericCrudDao;
import com.aa.whattoplay.games.domain.igdb.JsonFilesManager;
import com.aa.whattoplay.games.domain.igdb.json.CollectionJson;
import com.aa.whattoplay.games.infastructure.entities.igdb.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class ExternalDataCacherService {
    private final JsonFilesManager jsonFilesManager;
    private final IGenericCrudDao<Collection> gameEntityCrudDao;

    public void cacheCollections(){
        List<CollectionJson> collectionJsons = (List<CollectionJson>) jsonFilesManager.getAllObjectsFromJsons("./collections/", CollectionJson.class);
        gameEntityCrudDao.setClazz(Collection.class);
        collectionJsons.forEach( collectionJson ->
                gameEntityCrudDao.save(collectionJson.entity())
        );
    }

}
