package com.aa.whattoplay.games.domain.igdb;

import com.aa.ddd.common.annotations.DomainService;
import com.aa.ddd.common.domain.IGenericCrudDao;
import com.aa.whattoplay.games.domain.igdb.json.*;
import com.aa.whattoplay.games.domain.igdb.value.External;
import com.aa.whattoplay.games.infastructure.entities.igdb.*;
import com.aa.whattoplay.games.infastructure.entities.igdb.CollectionEntity;
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
    private final IGenericCrudDao<GameEntity> gameEntityGenericCrudDao;
    private final IGenericCrudDao<DeveloperEntity> developerGenericCrudDao;
    private final IGenericCrudDao<FranchiseEntity> franchiseGenericCrudDao;
    private final IGenericCrudDao<PlayerPerspectiveEntity> playerPerspectiveGenericCrudDao;
    private final IGenericCrudDao<GameModeEntity> gameModeEntityGenericCrudDao;
    private final IGenericCrudDao<GenreEntity> genreGenericCrudDao;
    private final IGenericCrudDao<CollectionEntity> collectionIGenericCrudDao;
    private final IGenericCrudDao<WebsiteEntity> websiteEntityGenericCrudDao;
    private HashMap<String, Integer> numberOfInconsistentData;

    @Autowired
    public JsonGamesPersistenceService(IGenericCrudDao<GameEntity> gameEntityGenericCrudDao, IGenericCrudDao<DeveloperEntity> developerGenericCrudDao,
                                       IGenericCrudDao<FranchiseEntity> franchiseGenericCrudDao, IGenericCrudDao<PlayerPerspectiveEntity> playerPerspectiveGenericCrudDao,
                                       IGenericCrudDao<GameModeEntity> gameModeEntityGenericCrudDao, IGenericCrudDao<GenreEntity> genreGenericCrudDao,
                                       IGenericCrudDao<CollectionEntity> collectionIGenericCrudDao, IGenericCrudDao<WebsiteEntity> websiteEntityGenericCrudDao) {
        this.gameEntityGenericCrudDao = gameEntityGenericCrudDao;
        this.developerGenericCrudDao = developerGenericCrudDao;
        this.franchiseGenericCrudDao = franchiseGenericCrudDao;
        this.playerPerspectiveGenericCrudDao = playerPerspectiveGenericCrudDao;
        this.gameModeEntityGenericCrudDao = gameModeEntityGenericCrudDao;
        this.genreGenericCrudDao = genreGenericCrudDao;
        this.collectionIGenericCrudDao = collectionIGenericCrudDao;
        this.websiteEntityGenericCrudDao = websiteEntityGenericCrudDao;
        this.gameEntityGenericCrudDao.setClazz(GameEntity.class);
        this.developerGenericCrudDao.setClazz(DeveloperEntity.class);
        this.franchiseGenericCrudDao.setClazz(FranchiseEntity.class);
        this.playerPerspectiveGenericCrudDao.setClazz(PlayerPerspectiveEntity.class);
        this.gameModeEntityGenericCrudDao.setClazz(GameModeEntity.class);
        this.genreGenericCrudDao.setClazz(GenreEntity.class);
        this.collectionIGenericCrudDao.setClazz(CollectionEntity.class);
        this.websiteEntityGenericCrudDao.setClazz(WebsiteEntity.class);
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
        GameEntity gameEntityToPersist = buildGameEntityFromJson(
                gameJson,
                getDevelopersOfGameJson(Optional.ofNullable(gameJson.getDevelopersIds()).orElse(new ArrayList<>()), gameId),
                getGameModesOfGameJson(Optional.ofNullable(gameJson.getGameModesIds()).orElse(new ArrayList<>()), gameId),
                getPlayerPerspectivesOfGameJson(Optional.ofNullable(gameJson.getPlayerPerspectivesIds()).orElse(new ArrayList<>()), gameId),
                getGenresOfGameJson(Optional.ofNullable(gameJson.getGenresIds()).orElse(new ArrayList<>()), gameId),
                getFranchiseOfGameJson(gameJson),
                getCollectionOfGameJson(gameJson)
        );
        gameEntityGenericCrudDao.save(gameEntityToPersist);
        Set<WebsiteEntity> websiteEntityEntities = getWebsitesOfGameJson(Optional.ofNullable(gameJson.getWebsites()).orElse(new ArrayList<>()), gameEntityToPersist);
        gameEntityToPersist.setWebsiteEntities(websiteEntityEntities);
        websiteEntityEntities.forEach(websiteEntityGenericCrudDao::save);
        gameEntityGenericCrudDao.update(gameEntityToPersist);
    }

    protected Set<DeveloperEntity> getDevelopersOfGameJson(List<Long> developersId, long gameId) {
        return developersId.stream()
                .map(
                        developerId ->
                                developerGenericCrudDao
                                        .findById(developerId)
                                        .orElseGet(() -> {
                                            numberOfInconsistentData.merge("Number of inconsistent developerEntities", 1, Integer::sum);
                                            log.info("Couldn't find developer for gameEntity: " + gameId + " developerId: " + developerId);
                                            return developerGenericCrudDao.findById((long) 0).get();
                                        })
                )
                .collect(Collectors.toSet());
    }

    protected Set<GameModeEntity> getGameModesOfGameJson(List<Short> gameModesId, long gameId) {
        return gameModesId.stream()
                .map(
                        gameModsId ->
                                gameModeEntityGenericCrudDao
                                        .findById((long) gameModsId)
                                        .orElseGet(() -> {
                                            numberOfInconsistentData.merge("Number of inconsistent gameEntity modes", 1, Integer::sum);
                                            log.info("Couldn't find gameEntity mode for gameEntity: " + gameId + " gameModeId: " + gameModsId);
                                            return gameModeEntityGenericCrudDao.findById((long) 0).get();
                                        })
                )
                .collect(Collectors.toSet());
    }

    protected Set<PlayerPerspectiveEntity> getPlayerPerspectivesOfGameJson(List<Short> playerPerspectivesIds, long gameId) {
        return playerPerspectivesIds.stream()
                .map(
                        playerPerspectiveId ->
                                playerPerspectiveGenericCrudDao
                                        .findById((long) playerPerspectiveId)
                                        .orElseGet(() -> {
                                            numberOfInconsistentData.merge("Number of inconsistent player perspectives", 1, Integer::sum);
                                            log.info("Couldn't find player perspective for gameEntity: " + gameId + " playerPerspectiveId: " + playerPerspectiveId);
                                            return playerPerspectiveGenericCrudDao.findById((long) 0).get();
                                        })
                )
                .collect(Collectors.toSet());
    }

    protected Set<GenreEntity> getGenresOfGameJson(List<Short> genresIds, long gameId) {
        return genresIds.stream()
                .map(
                        genreId ->
                                genreGenericCrudDao
                                        .findById((long) genreId)
                                        .orElseGet(() -> {
                                            numberOfInconsistentData.merge("Number of inconsistent genreEntities", 1, Integer::sum);
                                            log.info("Couldn't find genre for gameEntity: " + gameId);
                                            return genreGenericCrudDao.findById((long) 0).get();
                                        })
                )
                .collect(Collectors.toSet());
    }

    protected FranchiseEntity getFranchiseOfGameJson(GameJson gameJson) {
        return franchiseGenericCrudDao
                .findById(gameJson.getFranchiseId())
                .orElseGet(() -> {
                    numberOfInconsistentData.merge("Number of inconsistent franchises", 1, Integer::sum);
                    log.info("Couldn't find franchiseEntity for gameEntity: " + gameJson.getId() + " franchiseId: " + gameJson.getFranchiseId());
                    return franchiseGenericCrudDao.findById((long) 0).get();
                });
    }

    protected CollectionEntity getCollectionOfGameJson(GameJson gameJson) {
        return collectionIGenericCrudDao
                .findById(gameJson.getCollectionId())
                .orElseGet(() -> {
                    numberOfInconsistentData.merge("Number of inconsistent collections", 1, Integer::sum);
                    log.info("Couldn't find collectionEntity for gameEntity: " + gameJson.getId() + " collectionId: " + gameJson.getCollectionId());
                    return collectionIGenericCrudDao.findById((long) 0).get();
                });
    }

    protected Set<WebsiteEntity> getWebsitesOfGameJson(List<WebsiteJson> websiteJsons, GameEntity gameEntity) {
        return websiteJsons.stream()
                .map(x -> x.entity(gameEntity))
                .collect(Collectors.toSet());
    }

    protected GameEntity buildGameEntityFromJson(GameJson gameJson, Set<DeveloperEntity> developerEntities, Set<GameModeEntity> gameModeEntities,
                                                 Set<PlayerPerspectiveEntity> playerPerspectiveEntities, Set<GenreEntity> genreEntities,
                                                 FranchiseEntity franchiseEntity, CollectionEntity collectionEntity) {
        return GameEntity.builder()
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
                .collectionEntity(collectionEntity)
                .franchiseEntity(franchiseEntity)
                .developerEntities(developerEntities)
                .gameModeEntities(gameModeEntities)
                .genreEntities(genreEntities)
                .playerPerspectiveEntities(playerPerspectiveEntities)
                .websiteEntities(new HashSet<>())
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
        numberOfInconsistentData.put("Number of inconsistent developerEntities", 0);
        numberOfInconsistentData.put("Number of inconsistent gameEntity modes", 0);
        numberOfInconsistentData.put("Number of inconsistent player perspectives", 0);
        numberOfInconsistentData.put("Number of inconsistent genreEntities", 0);
        numberOfInconsistentData.put("Number of inconsistent franchises", 0);
        numberOfInconsistentData.put("Number of inconsistent collections", 0);
    }

}
