package com.aa.whattoplay.games.domain.igdb;

import com.aa.whattoplay.games.domain.igdb.json.GameJson;

import java.util.List;

public interface IGetGamesFromExternalSourceService {
    List<GameJson> getGamesFromExternalSource();
}
