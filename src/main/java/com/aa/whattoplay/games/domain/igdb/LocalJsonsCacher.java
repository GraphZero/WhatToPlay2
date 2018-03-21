package com.aa.whattoplay.games.domain.igdb;

import com.aa.ddd.common.annotations.DomainService;
import com.aa.ddd.common.domain.IGenericCrudDao;
import com.aa.whattoplay.games.domain.igdb.json.GameJson;
import com.aa.whattoplay.games.infastructure.entities.Developer;
import com.aa.whattoplay.games.infastructure.entities.GameEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class LocalJsonsCacher {
    private final JsonFilesManager jsonFilesManager;
    private final IGenericCrudDao<GameEntity> gameEntityCrudDao;

    public void persistGamesFromFiles(){
        gameEntityCrudDao.setClazz(GameEntity.class);
        List<GameJson> gameJsons = jsonFilesManager.getAllGamesFromJsonFiles("./games/");
    }

}
