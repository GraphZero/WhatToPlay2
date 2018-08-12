package com.aa.whattoplay.games.ui;


import com.aa.ddd.common.domain.IGenericCrudDao;
import com.aa.whattoplay.games.application.SuggestionsService;
import com.aa.whattoplay.games.application.commands.AddUserRating;
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
        DecisionTreeClassifier.process();
        return ResponseEntity.ok("ok");
    }

    @CrossOrigin
    @RequestMapping(path = "/addRating", method = RequestMethod.POST)
    public ResponseEntity<String> saveCsv(@RequestParam final long userId,
                                          @RequestParam final long gameId,
                                          @RequestParam final UserRating userRating) {
        suggestionsService.addUserRating(new AddUserRating(userId, gameId, userRating));
        return ResponseEntity.ok("Rating was added.");
    }

}
