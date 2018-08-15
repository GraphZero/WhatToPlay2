package com.aa.whattoplay.games.ui;


import com.aa.whattoplay.games.application.SuggestionsService;
import com.aa.whattoplay.games.application.commands.AddUserRating;
import com.aa.whattoplay.games.application.queries.QueryRecommendedGamesForUser;
import com.aa.whattoplay.games.domain.suggestions.RecommendedGames;
import com.aa.whattoplay.games.domain.suggestions.value.UserRating;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(path = "/process", method = RequestMethod.GET)
    public ResponseEntity<String> process() throws Exception {
        return ResponseEntity.ok("ok");
    }

    @CrossOrigin
    @RequestMapping(path = "/addRating", method = RequestMethod.POST)
    public ResponseEntity<String> addRatingForUser(@RequestParam final long userId,
                                                   @RequestParam final long gameId,
                                                   @RequestParam final UserRating userRating) {
        log.debug("Adding rating for user " + userId + " to a game " + gameId);
        suggestionsService.addUserRating(new AddUserRating(userId, gameId, userRating));
        return ResponseEntity.ok("\"Rating was added.\"");
    }

}
