package com.aa.whattoplay.games.domain.igdb;

import com.aa.ddd.common.annotations.ApplicationService;
import com.aa.whattoplay.games.domain.igdb.json.GenreJson;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@ApplicationService
@RequiredArgsConstructor
public class IgdbCachingService {
    private final IgdbJsonRequester igdbJsonRequester;

    public void cacheGenresFromIgdb() {
        List<GenreJson> genreJsons = igdbJsonRequester.getAllGenresFromIgdb();
    }

    public void cacheGameModesFromIgdb() {

    }

    public void cachePlayerPerspectivesFromIgdb() {

    }

    public void cacheFranchisesFromIgdb() {

    }

    public void cacheDevelopersFromIgdb() {

    }

    public void cacheCollectionsFromIgdb() {

    }

    public void cacheGamesFromIgdb() {

    }


}
