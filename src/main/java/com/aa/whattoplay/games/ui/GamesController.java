package com.aa.whattoplay.games.ui;

import com.aa.whattoplay.games.domain.suggestions.GameRepository;
import com.aa.whattoplay.games.domain.suggestions.value.GameDto;
import com.aa.whattoplay.games.infastructure.entities.igdb.GameEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GamesController {
    private final GameRepository gameRepository;


    @CrossOrigin
    @RequestMapping(path = "/findGameContaining", method = RequestMethod.GET)
    public ResponseEntity<Set<GameDto>> getGameContainingString(@RequestParam final String field) throws Exception {
        return ResponseEntity.ok(gameRepository
                .getByNameContaining(field)
                .stream()
                .limit(10)
                .map(GameEntity::dto)
                .collect(Collectors.toSet()));
    }


}
