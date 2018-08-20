package com.aa.whattoplay.games.domain.suggestions.implementation;

import com.aa.ddd.common.annotations.DomainService;
import com.aa.whattoplay.games.domain.suggestions.GameEvaluation;
import com.aa.whattoplay.games.domain.suggestions.RecommendedGames;
import com.aa.whattoplay.games.domain.suggestions.value.GameDto;
import com.aa.whattoplay.games.infastructure.entities.accounts.User;
import com.aa.whattoplay.games.infastructure.entities.accounts.UserPersonalRating;
import com.aa.whattoplay.games.infastructure.entities.igdb.GameEntity;
import com.aa.whattoplay.games.infastructure.repos.UserRatingsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@DomainService
@Slf4j
public class DecisionTreeSuggestionService implements SuggestionService {
    private final CsvFileSaver csvFileSaver;
    private final DecisionTreeClassifier decisionTreeClassifier;
    private final UserRatingsRepository userRatingsRepository;
    private final GameRepository gameRepository;

    @Override
    public RecommendedGames suggestGamesForUser(User user) {
        log.debug("Started creating suggestions for user " + user.getId());
        try {
            learnClassifier(user.getId(), csvFileSaver.getPath(Long.toString(user.getId())));
            suggestGames(user.getId());
            return new RecommendedGames(user.getId(), List.of());
        } catch (Exception e) {
            log.error("Exception happened " + e.getMessage());
            e.printStackTrace();
            return new RandomSuggestionService(gameRepository).suggestGamesForUser(user);
        }
    }

    void learnClassifier(long userId, String trainingDataSetPath) throws Exception{
            List<Long> latestRatings = userRatingsRepository.getLatestRatings(userId);
            if ( latestRatings.size() == 0 ) throw new Exception("No Evaluations yet!");
            decisionTreeClassifier.learnDecisionTree(trainingDataSetPath);
    }

    private List<GameDto> suggestGames(long userId) throws Exception{
        Collection<GameEntity> randomGames = gameRepository.getSeveralRandomGames(100);
        List<List<Object>> attributes = randomGames.stream().map(GameEntity::value).map(GameEvaluation::getLearnableAttributes).collect(Collectors.toList());
        String filePath = csvFileSaver.saveManyAttributesToCsvFile(attributes, "tmp/" + Long.toString(userId));
        decisionTreeClassifier.classifySet(filePath);
        return null;
    }

    private List<List<Object>> getAttributesForUser(long userId) throws Exception {
        List<List<Object>> evaluations = new ArrayList<>();
        List<Long> latestRatings = userRatingsRepository.getLatestRatings(userId);
        if ( latestRatings.size() == 0 ) throw new Exception("No Evaluations yet!");
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
