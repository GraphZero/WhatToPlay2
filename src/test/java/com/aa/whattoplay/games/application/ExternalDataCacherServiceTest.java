package com.aa.whattoplay.games.application;

import com.aa.ddd.common.domain.IGenericCrudDao;
import com.aa.whattoplay.games.domain.igdb.JsonFilesManager;
import com.aa.whattoplay.games.infastructure.entities.igdb.Collection;
import com.aa.whattoplay.games.infastructure.entities.igdb.Franchise;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
class ExternalDataCacherServiceTest {
    private ExternalDataCacherService externalDataCacherService;
    private JsonFilesManager jsonFilesManager;

    @Mock
    private IGenericCrudDao<Collection> collectionIGenericCrudDao;

    @Mock
    private IGenericCrudDao<Franchise> franchiseIGenericCrudDao;

//    @BeforeEach
//    void setExternalDataCacherService(){
//        jsonFilesManager = new JsonFilesManager();
//        externalDataCacherService = new ExternalDataCacherService(jsonFilesManager, collectionIGenericCrudDao, franchiseIGenericCrudDao);
//    }
//
//    @Test
//    void cacheCollections() {
//        // given
//        // when
//        // then
//        externalDataCacherService.cacheCollections();
//    }
}