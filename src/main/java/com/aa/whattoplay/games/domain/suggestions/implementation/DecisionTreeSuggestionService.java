package com.aa.whattoplay.games.domain.suggestions.implementation;

import com.aa.ddd.common.annotations.DomainService;
import com.aa.ddd.common.domain.IGenericCrudDao;
import com.aa.whattoplay.games.domain.suggestions.ISuggestionService;
import com.aa.whattoplay.games.domain.suggestions.RecommendedGames;
import com.aa.whattoplay.games.infastructure.entities.accounts.User;
import com.aa.whattoplay.games.infastructure.entities.igdb.GameEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@RequiredArgsConstructor
@DomainService
@Slf4j
public class DecisionTreeSuggestionService implements ISuggestionService {
    private final CsvFileSaver csvFileSaver;
    private final IGenericCrudDao<GameEntity> gamesDao;

    @Override
    public RecommendedGames suggestGamesForUser(User user) {
        try {
            csvFileSaver.saveAttributesToCsvFile(gamesDao.findById(1L).get().value().getLearnableAttributes());
        } catch (IOException e) {
            log.debug("lol bug " + e.getMessage());
        }
        return null;
    }
}
