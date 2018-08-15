package com.aa.whattoplay.games.domain.suggestions.implementation;

import com.aa.ddd.common.annotations.DomainService;
import com.aa.whattoplay.games.domain.suggestions.GameEvaluation;
import com.aa.whattoplay.games.domain.suggestions.RecommendedGames;
import com.aa.whattoplay.games.domain.suggestions.value.UserRating;
import com.aa.whattoplay.games.infastructure.entities.accounts.User;
import com.aa.whattoplay.games.infastructure.entities.accounts.UserPersonalRating;
import com.aa.whattoplay.games.infastructure.entities.igdb.GameEntity;
import com.aa.whattoplay.games.infastructure.repos.UserRatingsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@DomainService
@Slf4j
public class DecisionTreeSuggestionService implements SuggestionService {
    private final CsvFileSaver csvFileSaver;
    private final DecisionTreeClassifier decisionTreeClassifier;
    private final UserRatingsRepository userRatingsRepository;

    @Override
    public RecommendedGames suggestGamesForUser(User user) {
        log.debug("Started creating suggestions for user " + user.getId());
        String trainingDataSetPath = saveUsersRatedGames(user.getId());
        learnClassifier(trainingDataSetPath);
        return new RecommendedGames(user.getId(), List.of());
    }

    void learnClassifier(String trainingDataSetPath){
        try {
            decisionTreeClassifier.process(trainingDataSetPath);
        } catch (Exception e) {
            log.debug("Couldn't learn classifier because of  " + e.getMessage());
        }
    }

    private String saveUsersRatedGames(Long userId){
        try {
            return csvFileSaver.saveManyAttributesToCsvFile(getAttributesForUser(userId), userId.toString());
        } catch (IOException e) {
            log.debug("Couldn't save attributes fo csv file " + e.getMessage());
        }
        return "";
    }

    private List<List<Object>> getAttributesForUser(long userId) {
        List<List<Object>> evaluations = new ArrayList<>();
        List<Long> latestRatings = userRatingsRepository.getLatestRatings(userId);
        List<GameEntity> gamesForIds = userRatingsRepository.getGamesForIds(latestRatings);
        for (int i = 0; i < latestRatings.size(); i++) {
            UserPersonalRating userRating = userRatingsRepository.findById(latestRatings.get(i)).get();
            GameEvaluation gameEvaluation = gamesForIds.get(i).value();
            gameEvaluation.setUserRating(userRating.getRating());
            evaluations.add(gameEvaluation.getLearnableAttributes());
        }
        return evaluations;
    }

}
