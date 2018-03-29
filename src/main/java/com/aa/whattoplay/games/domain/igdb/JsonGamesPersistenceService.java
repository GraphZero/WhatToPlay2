package com.aa.whattoplay.games.domain.igdb;

import com.aa.ddd.common.annotations.DomainService;
import com.aa.ddd.common.domain.IGenericCrudDao;
import com.aa.whattoplay.games.domain.igdb.json.*;
import com.aa.whattoplay.games.domain.igdb.value.External;
import com.aa.whattoplay.games.infastructure.entities.igdb.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@DomainService
@Transactional
@Slf4j
public class JsonGamesPersistenceService {
    private final IGenericCrudDao<GameEntity> gameEntityGenericCrudDao;
    private final IGenericCrudDao<Developer> developerGenericCrudDao;
    private final IGenericCrudDao<Franchise> franchiseGenericCrudDao;
    private final IGenericCrudDao<PlayerPerspective> playerPerspectiveGenericCrudDao;
    private final IGenericCrudDao<GameModeEntity> gameModeEntityGenericCrudDao;
    private final IGenericCrudDao<Genre> genreGenericCrudDao;
    private final IGenericCrudDao<Collection> collectionIGenericCrudDao;
    private final IGenericCrudDao<WebsiteEntity> websiteEntityGenericCrudDao;


    @Autowired
    public JsonGamesPersistenceService(IGenericCrudDao<GameEntity> gameEntityGenericCrudDao, IGenericCrudDao<Developer> developerGenericCrudDao,
                                       IGenericCrudDao<Franchise> franchiseGenericCrudDao, IGenericCrudDao<PlayerPerspective> playerPerspectiveGenericCrudDao,
                                       IGenericCrudDao<GameModeEntity> gameModeEntityGenericCrudDao, IGenericCrudDao<Genre> genreGenericCrudDao,
                                       IGenericCrudDao<Collection> collectionIGenericCrudDao, IGenericCrudDao<WebsiteEntity> websiteEntityGenericCrudDao) {
        this.gameEntityGenericCrudDao = gameEntityGenericCrudDao;
        this.developerGenericCrudDao = developerGenericCrudDao;
        this.franchiseGenericCrudDao = franchiseGenericCrudDao;
        this.playerPerspectiveGenericCrudDao = playerPerspectiveGenericCrudDao;
        this.gameModeEntityGenericCrudDao = gameModeEntityGenericCrudDao;
        this.genreGenericCrudDao = genreGenericCrudDao;
        this.collectionIGenericCrudDao = collectionIGenericCrudDao;
        this.websiteEntityGenericCrudDao = websiteEntityGenericCrudDao;
        this.gameEntityGenericCrudDao.setClazz(GameEntity.class);
        this.developerGenericCrudDao.setClazz(Developer.class);
        this.franchiseGenericCrudDao.setClazz(Franchise.class);
        this.playerPerspectiveGenericCrudDao.setClazz(PlayerPerspective.class);
        this.gameModeEntityGenericCrudDao.setClazz(GameModeEntity.class);
        this.genreGenericCrudDao.setClazz(Genre.class);
        this.collectionIGenericCrudDao.setClazz(Collection.class);
        this.websiteEntityGenericCrudDao.setClazz(WebsiteEntity.class);
    }

    public long persistGameJsons(List<GameJson> gameJsonList) {
        return 0;
    }

    public boolean persistGameJson(GameJson gameJson) {
        GameEntity gameToPersist = buildGameEntityFromJson(
                    gameJson,
                    getDevelopersOfGameJson(gameJson),
                    getGameModesOfGameJson(gameJson),
                    getPlayerPerspectivesOfGameJson(gameJson),
                    getGenresOfGameJson(gameJson),
                    getFranchiseOfGameJson(gameJson),
                    getCollectionOfGameJson(gameJson)
        );
        Set<WebsiteEntity> websiteEntities = getWebsitesOfGameJson(gameJson, gameToPersist);
        gameToPersist.setWebsites(websiteEntities);
        websiteEntities.forEach(websiteEntityGenericCrudDao::save);
        gameEntityGenericCrudDao.save(gameToPersist);
        return true;
    }

    protected Set<PlayerPerspective> getPlayerPerspectivesOfGameJson(GameJson gameJson) {
        return gameJson.getPlayerPerspectivesIds().stream()
                .map(
                        playerPerspectiveId ->
                                playerPerspectiveGenericCrudDao
                                        .findById((long) playerPerspectiveId)
                                        .orElseGet(() -> {
                                            log.warn("Couldn't find player perspective for game: " + gameJson.getId());
                                            return PlayerPerspective.builder().id(0).name("UNKNOWN PLAYER PERSPECTIVE").build();
                                        })
                )
                .collect(Collectors.toSet());
    }

    protected Set<GameModeEntity> getGameModesOfGameJson(GameJson gameJson) {
        return gameJson.getGameModesIds().stream()
                .map(
                        gameModsId ->
                                gameModeEntityGenericCrudDao
                                        .findById( (long) gameModsId)
                                        .orElseGet(() -> {
                                            log.warn("Couldn't find game mode for game: " + gameJson.getId());
                                            return GameModeEntity.builder().id(0).name("UNKNOWN GAME MODE").build();
                                        })
                )
                .collect(Collectors.toSet());
    }

    protected Set<Developer> getDevelopersOfGameJson(GameJson gameJson) {
        return gameJson.getDevelopersIds().stream()
                .map(
                        developerId ->
                                developerGenericCrudDao
                                        .findById(developerId)
                                        .orElseGet(() -> {
                                            log.warn("Couldn't find developer for game: " + gameJson.getId());
                                            return Developer.builder().id(0).name("UNKNOWN DEVELOPER").build();
                                        })
                )
                .collect(Collectors.toSet());
    }

    protected Set<Genre> getGenresOfGameJson(GameJson gameJson) {
        return gameJson.getGenresIds().stream()
                .map(
                        genreId ->
                                genreGenericCrudDao
                                        .findById((long) genreId)
                                        .orElseGet(() -> {
                                            log.warn("Couldn't find genre for game: " + gameJson.getId());
                                            return Genre.builder().id(0).name("UNKNOWN GENRE").build();
                                        })
                )
                .collect(Collectors.toSet());
    }

    protected Franchise getFranchiseOfGameJson(GameJson gameJson) {
        return franchiseGenericCrudDao.findById(gameJson.getId())
                .orElseGet(() -> {
                    log.warn("Couldn't find franchise for game: " + gameJson.getId());
                    return Franchise.builder().id(0).name("UNDEFINED FRANCHISE").build();
                });
    }

    protected Collection getCollectionOfGameJson(GameJson gameJson) {
        return collectionIGenericCrudDao.findById(gameJson.getId())
                .orElseGet(() -> {
                    log.warn("Couldn't find collection for game: " + gameJson.getId());
                    return Collection.builder().id(0).name("UNDEFINED COLLECTION").build();
                });
    }

    protected Set<WebsiteEntity> getWebsitesOfGameJson(GameJson gameJson, GameEntity game) {
        return gameJson.getWebsites().stream()
                .map(x -> x.entity(game))
                .collect(Collectors.toSet());
    }

    protected GameEntity buildGameEntityFromJson(GameJson gameJson, Set<Developer> developers, Set<GameModeEntity> gameModes,
                                                 Set<PlayerPerspective> playerPerspectives, Set<Genre> genres,
                                                 Franchise franchise, Collection collection) {
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


}
