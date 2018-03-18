package com.aa.whattoplay.games.domain.igdb;

import com.aa.ddd.common.annotations.ApplicationService;
import com.aa.whattoplay.games.domain.igdb.json.GameJson;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationService
@RequiredArgsConstructor
public class GetGamesFromIgdbService implements IGetGamesFromExternalSourceService{
    private final IgdbGamesJsonRequester igdbGamesJsonRequester;
    private final JsonGameSaverService jsonGameSaverService;

    @Override
    public boolean getGames() {
        List<GameJson> games = igdbGamesJsonRequester.getAllGamesFromIgdb();
        jsonGameSaverService.saveGames(games);
        return false;
    }

}
