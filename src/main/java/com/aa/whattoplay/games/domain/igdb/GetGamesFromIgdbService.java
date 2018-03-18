package com.aa.whattoplay.games.domain.igdb;

import com.aa.ddd.common.annotations.ApplicationService;
import com.aa.whattoplay.games.domain.igdb.json.GameJson;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationService
@RequiredArgsConstructor
public class GetGamesFromIgdbService{
    private final IgdbGamesJsonRequester igdbGamesJsonRequester;
    private final JsonGameFileCacher jsonGameFileCacher;

    public void getGamesFromIgdb() {
        List<GameJson> games = igdbGamesJsonRequester.getGamesFromExternalSource();
        jsonGameFileCacher.saveGames(games);
    }

}
