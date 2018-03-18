package com.aa.whattoplay.games.domain.igdb;

import com.aa.ddd.common.annotations.DomainService;
import com.aa.whattoplay.games.domain.igdb.json.GameJson;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class IgdbGamesFromFilesCacher {
    private final GamesFromJsonFilesLoader gamesFromJsonFilesLoader;

    public void persistGamesFromFiles(){
        List<GameJson> gameJsons = gamesFromJsonFilesLoader.getGamesFromExternalSource();

    }

}
