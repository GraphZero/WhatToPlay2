package com.aa.whattoplay.games.ui;


import com.aa.ddd.common.domain.IGenericCrudDao;
import com.aa.whattoplay.games.application.SuggestionsService;
import com.aa.whattoplay.games.application.queries.QueryRecommendedGamesForUser;
import com.aa.whattoplay.games.domain.suggestions.GameEvaluation;
import com.aa.whattoplay.games.domain.suggestions.RecommendedGames;
import com.aa.whattoplay.games.domain.suggestions.UserRating;
import com.aa.whattoplay.games.domain.suggestions.implementation.CsvFileSaver;
import com.aa.whattoplay.games.domain.suggestions.implementation.DecisionTreeClassifier;
import com.aa.whattoplay.games.infastructure.entities.igdb.GameEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import weka.core.converters.CSVSaver;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SuggestionController {
    private final SuggestionsService suggestionsService;
     private final CsvFileSaver csvSaver;
    private final IGenericCrudDao<GameEntity> gamesDao;

    @CrossOrigin
    @RequestMapping(path = "/getSuggestionsForUser", method = RequestMethod.GET)
    public ResponseEntity<RecommendedGames> getSuggestionsForUser(@RequestParam final long userId) {
        return suggestionsService
                .queryRecommendedGamesForUser(new QueryRecommendedGamesForUser(userId))
                .map(recommendedGames ->
                        new ResponseEntity<>(recommendedGames, HttpStatus.OK)
                )
                .orElseGet(() ->
                        new ResponseEntity<>(new RecommendedGames(-1, null), HttpStatus.CONFLICT)
                );
    }

    @CrossOrigin
    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> saveCsv() throws Exception {
        gamesDao.setClazz(GameEntity.class);
        GameEntity gameEntity = gamesDao.findById(1L).get();
        GameEntity gameEntity1 = gamesDao.findById(2L).get();
        GameEvaluation gameEvaluation = gameEntity.value();
        GameEvaluation gameEvaluation1 = gameEntity1.value();
        gameEvaluation.setUserRating(UserRating.INTERESTED);
        gameEvaluation1.setUserRating(UserRating.NOT_INTERESTED);
        csvSaver.saveAttributesToCsvFile(gameEvaluation.getLearnableAttributes());
        csvSaver.saveAttributesToCsvFile(gameEvaluation1.getLearnableAttributes());
        DecisionTreeClassifier.process();
        return ResponseEntity.ok("ok");
    }

}
