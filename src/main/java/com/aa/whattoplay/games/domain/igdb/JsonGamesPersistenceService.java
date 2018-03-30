package com.aa.whattoplay.games.domain.igdb;

import com.aa.ddd.common.annotations.DomainService;
import com.aa.ddd.common.domain.IGenericCrudDao;
import com.aa.whattoplay.games.domain.igdb.json.*;
import com.aa.whattoplay.games.domain.igdb.value.External;
import com.aa.whattoplay.games.infastructure.entities.igdb.*;
import com.aa.whattoplay.games.infastructure.entities.igdb.Collection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@DomainService
@Transactional
@Slf4j
public class JsonGamesPersistenceService {
    private final IGenericCrudDao<Game> gameEntityGenericCrudDao;
    private final IGenericCrudDao<Developer> developerGenericCrudDao;
    private final IGenericCrudDao<Franchise> franchiseGenericCrudDao;
    private final IGenericCrudDao<PlayerPerspective> playerPerspectiveGenericCrudDao;
    private final IGenericCrudDao<GameMode> gameModeEntityGenericCrudDao;
    private final IGenericCrudDao<Genre> genreGenericCrudDao;
    private final IGenericCrudDao<Collection> collectionIGenericCrudDao;
    private final IGenericCrudDao<Website> websiteEntityGenericCrudDao;
    private HashMap<String, Integer> numberOfInconsistentData;

    @Autowired
    public JsonGamesPersistenceService(IGenericCrudDao<Game> gameEntityGenericCrudDao, IGenericCrudDao<Developer> developerGenericCrudDao,
                                       IGenericCrudDao<Franchise> franchiseGenericCrudDao, IGenericCrudDao<PlayerPerspective> playerPerspectiveGenericCrudDao,
                                       IGenericCrudDao<GameMode> gameModeEntityGenericCrudDao, IGenericCrudDao<Genre> genreGenericCrudDao,
                                       IGenericCrudDao<Collection> collectionIGenericCrudDao, IGenericCrudDao<Website> websiteEntityGenericCrudDao) {
        this.gameEntityGenericCrudDao = gameEntityGenericCrudDao;
        this.developerGenericCrudDao = developerGenericCrudDao;
        this.franchiseGenericCrudDao = franchiseGenericCrudDao;
        this.playerPerspectiveGenericCrudDao = playerPerspectiveGenericCrudDao;
        this.gameModeEntityGenericCrudDao = gameModeEntityGenericCrudDao;
        this.genreGenericCrudDao = genreGenericCrudDao;
        this.collectionIGenericCrudDao = collectionIGenericCrudDao;
        this.websiteEntityGenericCrudDao = websiteEntityGenericCrudDao;
        this.gameEntityGenericCrudDao.setClazz(Game.class);
        this.developerGenericCrudDao.setClazz(Developer.class);
        this.franchiseGenericCrudDao.setClazz(Franchise.class);
        this.playerPerspectiveGenericCrudDao.setClazz(PlayerPerspective.class);
        this.gameModeEntityGenericCrudDao.setClazz(GameMode.class);
        this.genreGenericCrudDao.setClazz(Genre.class);
        this.collectionIGenericCrudDao.setClazz(Collection.class);
        this.websiteEntityGenericCrudDao.setClazz(Website.class);
        initiliazeErrorHashMap();
    }

    public HashMap<String, Integer> persistGameJsons(List<GameJson> gameJsonList) {
        for (int i = 0; i < gameJsonList.size(); i++) {
            if ( i % 20 == 0 ) gameEntityGenericCrudDao.flushAndClearForBatchProcessing();
            if ( ( i + 1) % 1000 == 0 ) log.info("Persisted 1000 games");
            persistGameJson(gameJsonList.get(i));
        }
        return numberOfInconsistentData;
    }

    protected void persistGameJson(GameJson gameJson) {
        long gameId = gameJson.getId();
        Game gameToPersist = buildGameEntityFromJson(
                gameJson,
                getDevelopersOfGameJson(Optional.ofNullable(gameJson.getDevelopersIds()).orElse(new ArrayList<>()), gameId),
                getGameModesOfGameJson(Optional.ofNullable(gameJson.getGameModesIds()).orElse(new ArrayList<>()), gameId),
                getPlayerPerspectivesOfGameJson(Optional.ofNullable(gameJson.getPlayerPerspectivesIds()).orElse(new ArrayList<>()), gameId),
                getGenresOfGameJson(Optional.ofNullable(gameJson.getGenresIds()).orElse(new ArrayList<>()), gameId),
                getFranchiseOfGameJson(gameJson),
                getCollectionOfGameJson(gameJson)
        );
        gameEntityGenericCrudDao.save(gameToPersist);
        Set<Website> websiteEntities = getWebsitesOfGameJson(Optional.ofNullable(gameJson.getWebsites()).orElse(new ArrayList<>()), gameToPersist);
        gameToPersist.setWebsites(websiteEntities);
        websiteEntities.forEach(websiteEntityGenericCrudDao::save);
        gameEntityGenericCrudDao.update(gameToPersist);
    }

    protected Set<Developer> getDevelopersOfGameJson(List<Long> developersId, long gameId) {
        return developersId.stream()
                .map(
                        developerId ->
                                developerGenericCrudDao
                                        .findById(developerId)
                                        .orElseGet(() -> {
                                            numberOfInconsistentData.merge("Number of inconsistent developers", 1, Integer::sum);
                                            log.info("Couldn't find developer for game: " + gameId + " developerId: " + developerId);
                                            return developerGenericCrudDao.findById((long) 0).get();
                                        })
                )
                .collect(Collectors.toSet());
    }

    protected Set<GameMode> getGameModesOfGameJson(List<Short> gameModesId, long gameId) {
        return gameModesId.stream()
                .map(
                        gameModsId ->
                                gameModeEntityGenericCrudDao
                                        .findById((long) gameModsId)
                                        .orElseGet(() -> {
                                            numberOfInconsistentData.merge("Number of inconsistent game modes", 1, Integer::sum);
                                            log.info("Couldn't find game mode for game: " + gameId + " gameModeId: " + gameModsId);
                                            return gameModeEntityGenericCrudDao.findById((long) 0).get();
                                        })
                )
                .collect(Collectors.toSet());
    }

    protected Set<PlayerPerspective> getPlayerPerspectivesOfGameJson(List<Short> playerPerspectivesIds, long gameId) {
        return playerPerspectivesIds.stream()
                .map(
                        playerPerspectiveId ->
                                playerPerspectiveGenericCrudDao
                                        .findById((long) playerPerspectiveId)
                                        .orElseGet(() -> {
                                            numberOfInconsistentData.merge("Number of inconsistent player perspectives", 1, Integer::sum);
                                            log.info("Couldn't find player perspective for game: " + gameId + " playerPerspectiveId: " + playerPerspectiveId);
                                            return playerPerspectiveGenericCrudDao.findById((long) 0).get();
                                        })
                )
                .collect(Collectors.toSet());
    }

    protected Set<Genre> getGenresOfGameJson(List<Short> genresIds, long gameId) {
        return genresIds.stream()
                .map(
                        genreId ->
                                genreGenericCrudDao
                                        .findById((long) genreId)
                                        .orElseGet(() -> {
                                            numberOfInconsistentData.merge("Number of inconsistent genres", 1, Integer::sum);
                                            log.info("Couldn't find genre for game: " + gameId);
                                            return genreGenericCrudDao.findById((long) 0).get();
                                        })
                )
                .collect(Collectors.toSet());
    }

    protected Franchise getFranchiseOfGameJson(GameJson gameJson) {
        return franchiseGenericCrudDao
                .findById(gameJson.getFranchiseId())
                .orElseGet(() -> {
                    numberOfInconsistentData.merge("Number of inconsistent franchises", 1, Integer::sum);
                    log.info("Couldn't find franchise for game: " + gameJson.getId() + " franchiseId: " + gameJson.getFranchiseId());
                    return franchiseGenericCrudDao.findById((long) 0).get();
                });
    }

    protected Collection getCollectionOfGameJson(GameJson gameJson) {
        return collectionIGenericCrudDao
                .findById(gameJson.getCollectionId())
                .orElseGet(() -> {
                    numberOfInconsistentData.merge("Number of inconsistent collections", 1, Integer::sum);
                    log.info("Couldn't find collection for game: " + gameJson.getId() + " collectionId: " + gameJson.getCollectionId());
                    return collectionIGenericCrudDao.findById((long) 0).get();
                });
    }

    protected Set<Website> getWebsitesOfGameJson(List<WebsiteJson> websiteJsons, Game game) {
        return websiteJsons.stream()
                .map(x -> x.entity(game))
                .collect(Collectors.toSet());
    }

    protected Game buildGameEntityFromJson(GameJson gameJson, Set<Developer> developers, Set<GameMode> gameModes,
                                           Set<PlayerPerspective> playerPerspectives, Set<Genre> genres,
                                           Franchise franchise, Collection collection) {
        return Game.builder()
                .id(gameJson.getId())
                .name(gameJson.getName())
                .slug(gameJson.getSlug())
                .url(gameJson.getUrl())
                .summary(gameJson.getSummary())
                .storyline(gameJson.getStoryline())
                .hypes(gameJson.getHypes())
                .popularity(gameJson.getPopularity())
                .rating(gameJson.getRating())
                .ratingCount(gameJson.getRatingCount())
                .aggregatedRating(gameJson.getAggregatedRating())
                .aggregatedRatingCount(gameJson.getAggregatedRatingCount())
                .totalRating(gameJson.getTotalRating())
                .totalRatingCount(gameJson.getTotalRatingCount())
                .totalRating(gameJson.getTotalRating())
                .totalRatingCount(gameJson.getTotalRatingCount())
                .createdAt(Instant.ofEpochMilli(gameJson.getCreatedAt()).atZone(ZoneId.systemDefault()).toLocalDate())
                .updatedAt(Instant.ofEpochMilli(gameJson.getUpdatedAt()).atZone(ZoneId.systemDefault()).toLocalDate())
                .firstReleaseDate(Instant.ofEpochMilli(gameJson.getFirstReleaseDate()).atZone(ZoneId.systemDefault()).toLocalDate())
                .collection(collection)
                .franchise(franchise)
                .developers(developers)
                .gameModes(gameModes)
                .genres(genres)
                .playerPerspectives(playerPerspectives)
                .websites(new HashSet<>())
                .status(gameJson.getStatus())
                .timeToBeat(Optional.ofNullable(gameJson.getTimeToBeat()).orElse(new TimeToBeatJson()).entity())
                .esrb(Optional.ofNullable(gameJson.getEsrb()).orElse(new EsrbJson()).entity())
                .pegi(Optional.ofNullable(gameJson.getPegi()).orElse(new PegiJson()).entity())
                .external(Optional.ofNullable(gameJson.getExternal()).orElse(new External()))
                .cover(Optional.ofNullable(gameJson.getCover()).orElse(new ImageInfoJson()).entity())
                .build();
    }

    private void initiliazeErrorHashMap(){
        numberOfInconsistentData = new HashMap<>();
        numberOfInconsistentData.put("Number of inconsistent developers", 0);
        numberOfInconsistentData.put("Number of inconsistent game modes", 0);
        numberOfInconsistentData.put("Number of inconsistent player perspectives", 0);
        numberOfInconsistentData.put("Number of inconsistent genres", 0);
        numberOfInconsistentData.put("Number of inconsistent franchises", 0);
        numberOfInconsistentData.put("Number of inconsistent collections", 0);
    }

}
