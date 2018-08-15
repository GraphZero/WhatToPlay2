package com.aa.whattoplay.games.application;

import com.aa.ddd.common.annotations.ApplicationService;
import com.aa.ddd.common.domain.IGenericCrudDao;
import com.aa.whattoplay.games.application.commands.AddUserRating;
import com.aa.whattoplay.games.application.queries.QueryRecommendedGamesForUser;
import com.aa.whattoplay.games.domain.suggestions.RecommendedGames;
import com.aa.whattoplay.games.domain.suggestions.implementation.CsvFileSaver;
import com.aa.whattoplay.games.domain.suggestions.implementation.SuggestionService;
import com.aa.whattoplay.games.infastructure.entities.accounts.User;
import com.aa.whattoplay.games.infastructure.entities.accounts.UserPersonalRating;
import com.aa.whattoplay.games.infastructure.entities.igdb.GameEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;
import java.util.Optional;

@ApplicationService
@Slf4j
public class SuggestionsService {
    private final SuggestionService suggestionService;
    private final CsvFileSaver csvFileSaver;
    private final IGenericCrudDao<User> usersDao;
    private final IGenericCrudDao<UserPersonalRating> userRatingsDao;
    private final IGenericCrudDao<GameEntity> gamesDao;

    public SuggestionsService(@Qualifier("decisionTreeSuggestionService") SuggestionService suggestionService,
                              CsvFileSaver csvFileSaver, IGenericCrudDao<User> usersDao,
                              IGenericCrudDao<UserPersonalRating> userRatingsDao,
                              IGenericCrudDao<GameEntity> gamesDao) {
        this.suggestionService = suggestionService;
        this.csvFileSaver = csvFileSaver;
        this.usersDao = usersDao;
        this.userRatingsDao = userRatingsDao;
        this.gamesDao = gamesDao;
        usersDao.setClazz(User.class);
        userRatingsDao.setClazz(UserPersonalRating.class);
        gamesDao.setClazz(GameEntity.class);
    }

    public Optional<RecommendedGames> queryRecommendedGamesForUser(QueryRecommendedGamesForUser queryRecommendedGamesForUser) {
        return usersDao
                .findById(queryRecommendedGamesForUser.getUserId())
                .map(suggestionService::suggestGamesForUser);
    }

    public Optional<UserPersonalRating> addUserRating(AddUserRating addUserRating) {
        return usersDao
                .findById(addUserRating.getUserId())
                .map(user ->
                     gamesDao
                            .findById(addUserRating.getGameId())
                            .map(game -> {
                                UserPersonalRating userRating = new UserPersonalRating(user, game, addUserRating.getUserRating());
                                userRatingsDao.save(userRating);
                                try {
                                    csvFileSaver.saveAttributesToCsvFile(userRating.gameEvaluation().getLearnableAttributes(), Long.toString(user.getId()));
                                } catch (IOException e) {
                                    log.warn("Couldn't save user " + user.getId() + " rating. " );
                                    e.printStackTrace();
                                }
                                return userRating;
                            })
                            .orElse(null)
                );
    }

}
