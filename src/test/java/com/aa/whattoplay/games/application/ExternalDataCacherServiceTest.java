package com.aa.whattoplay.games.application;

import com.aa.ddd.common.domain.IGenericCrudDao;
import com.aa.whattoplay.games.domain.igdb.JsonFilesManager;
import com.aa.whattoplay.games.domain.igdb.JsonGamesPersistenceService;
import com.aa.whattoplay.games.infastructure.entities.igdb.CollectionEntity;
import com.aa.whattoplay.games.infastructure.entities.igdb.DeveloperEntity;
import com.aa.whattoplay.games.infastructure.entities.igdb.FranchiseEntity;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
class ExternalDataCacherServiceTest {
    private ExternalDataCacherService externalDataCacherService;
    private JsonFilesManager jsonFilesManager;

    @Mock
    private IGenericCrudDao<CollectionEntity> collectionIGenericCrudDao;

    @Mock
    private IGenericCrudDao<FranchiseEntity> franchiseIGenericCrudDao;

    @Mock
    private IGenericCrudDao<DeveloperEntity> developerIGenericCrudDao;

    @Mock
    private JsonGamesPersistenceService jsonGamesPersistenceService;

    @BeforeEach
    void setExternalDataCacherService(){
        jsonFilesManager = new JsonFilesManager();
        externalDataCacherService = new ExternalDataCacherService(jsonFilesManager, jsonGamesPersistenceService, collectionIGenericCrudDao, franchiseIGenericCrudDao, developerIGenericCrudDao);
    }

    @Test
    @Disabled
    void cacheCollections() {
        // given
        // when
        // then
        externalDataCacherService.persistGamesFromDefaultJsonFiles();
    }
}