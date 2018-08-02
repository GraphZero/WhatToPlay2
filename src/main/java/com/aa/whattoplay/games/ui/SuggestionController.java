package com.aa.whattoplay.games.ui;


import com.aa.whattoplay.games.application.SuggestionsService;
import com.aa.whattoplay.games.application.queries.QueryRecommendedGamesForUser;
import com.aa.whattoplay.games.domain.suggestions.RecommendedGames;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SuggestionController {
    private final SuggestionsService suggestionsService;

    @CrossOrigin
    @RequestMapping(path = "/getSuggestionsForUser", method = RequestMethod.GET)
    public ResponseEntity<RecommendedGames> getSuggestionsForUser(@RequestParam final long userId){
        return suggestionsService
                .queryRecommendedGamesForUser(new QueryRecommendedGamesForUser(userId))
                .map( recommendedGames ->
                    new ResponseEntity<>(recommendedGames, HttpStatus.OK)
                )
                .orElseGet( () ->
                    new ResponseEntity<>(new RecommendedGames(-1, null), HttpStatus.CONFLICT)
                );
    }

}