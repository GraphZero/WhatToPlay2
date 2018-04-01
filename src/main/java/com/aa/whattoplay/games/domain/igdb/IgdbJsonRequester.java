package com.aa.whattoplay.games.domain.igdb;

import com.aa.ddd.common.annotations.DomainService;
import com.aa.whattoplay.games.domain.igdb.json.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DomainService
public class IgdbJsonRequester  {
    private static final String TOKEN = "c984d9be38401fb2807d383c03d34599";
    private static Logger logger = LoggerFactory.getLogger(IgdbJsonRequester.class);

    @Autowired
    public IgdbJsonRequester() {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * This method returns scroll url to allow pagination of IGDB responses.
     * Without it, it is not possible to cache all reponses.
     *
     * @param url    and url to the endpoint
     * @param fields fields of the needed object
     * @return HttpRequest containing set of non-casted objects
     * @see <a href="https://igdb.github.io/api/references/pagination/">IGDB Pagination Page</a>
     */
    protected HttpRequest getScrollFromIGDB(String url, String fields) {
        return Unirest.get(url)
                .header("accept", "application/json")
                .header("user-key", TOKEN)
                .queryString("fields", fields)
                .queryString("limit", "50")
                .queryString("scroll", 1);
    }

    protected HttpRequest getSetOfObjectsFromIGDB(String url) {
        return Unirest.get("https://api-2445582011268.apicast.io/" + url)
                .header("accept", "application/json")
                .header("user-key", TOKEN);
    }

    public List<GenreJson> getAllGenresFromIgdb()  {
        HttpResponse<GenreJson[]> genresJson = null;
        try {
            genresJson = Unirest.get("https://api-2445582011268.apicast.io/genreEntities/")
                    .header("accept", "application/json")
                    .header("user-key", TOKEN)
                    .queryString("fields", getBasicFields())
                    .queryString("limit", "50")
                    .asObject(GenreJson[].class);
        } catch (UnirestException e) {
            logger.error(String.format(String.format("%sCouldn't parse Genres. \n%%s", this.getClass().getName()), e.getMessage()));
            throw new CouldntParseGenresException();
        }
        return Arrays.asList(genresJson.getBody()) ;
    }

    public List<GameModeJson> getAllGameModesFromIgdb() {
        HttpResponse<GameModeJson[]> genresJson;
        try {
            genresJson = Unirest.get("https://api-2445582011268.apicast.io/game_modes/")
                    .header("accept", "application/json")
                    .header("user-key", TOKEN)
                    .queryString("fields", getBasicFields())
                    .queryString("limit", "50")
                    .asObject(GameModeJson[].class);
        } catch (UnirestException e) {
            logger.error(new StringBuilder().append(String.format("%sCouldn't parse GameEntity Modes. \n", this.getClass().getName())).append(e.getMessage()).toString());
            throw new CouldntParseGameModesException();
        }
        return Arrays.asList(genresJson.getBody());
    }

    public List<PlayerPerspectiveJson> getAllPlayerPerspectivesFromIgdb()  {
        HttpResponse<PlayerPerspectiveJson[]> genresJson;
        try {
            genresJson = Unirest.get("https://api-2445582011268.apicast.io/player_perspectives/")
                    .header("accept", "application/json")
                    .header("user-key", TOKEN)
                    .queryString("fields", getBasicFields())
                    .queryString("limit", "50")
                    .asObject(PlayerPerspectiveJson[].class);
        } catch (UnirestException e) {
            logger.error(new StringBuilder().append(String.format("%sCouldn't parse Player Perspectives.\n ", this.getClass().getName())).append(e.getMessage()).toString());
            throw new CouldntParsePlayerPerspectivesException();
        }
        return Arrays.asList(genresJson.getBody());
    }

    public List<FranchiseJson> getAllFranchisesFromIgdb() {
        logger.info(" ===================== Persisting Franchises starts. ===================== ");
        final String urlForScroll = "https://api-2445582011268.apicast.io/franchises/";
        final String scrollUrlFranchises;
        final long requiredRequestsNumb;
        final HttpResponse<FranchiseJson[]> jsonResponse;
        List<FranchiseJson> franchiseJsons;
        try {
            jsonResponse = getScrollFromIGDB(urlForScroll, getBasicFields()).asObject(FranchiseJson[].class);
            scrollUrlFranchises = jsonResponse.getHeaders().get("X-Next-Page").get(0);
            requiredRequestsNumb = Math.round(Integer.parseInt(jsonResponse.getHeaders().get("X-Count").get(0)) / 50);
            logger.info(new StringBuilder().append(" Scroll url for requests: ").append(scrollUrlFranchises).toString());
            logger.info(new StringBuilder().append(" Got ").append(jsonResponse.getHeaders().get("X-Count").get(0)).append(" franchsises. ").toString());
            logger.info(new StringBuilder().append(" Doing ").append(requiredRequestsNumb + 1).append(" iterations. ").toString());
            franchiseJsons = new ArrayList<>(Arrays.asList(jsonResponse.getBody()));
            for (int i = 0; i < requiredRequestsNumb; i++) {
                try {
                    franchiseJsons.addAll(Arrays.asList(getSetOfObjectsFromIGDB(scrollUrlFranchises)
                            .asObject(FranchiseJson[].class)
                            .getBody()));
                } catch (UnirestException e) {
                    logger.info(new StringBuilder().append(" Got to the end of the franchises scroll! ").toString());
                }
            }
        } catch (UnirestException e) {
            logger.error(new StringBuilder().append(" Couldnt get the scroll for Franchises ").append(e.getMessage()).toString());
            throw new CouldntParseFranchisesException();
        }
        return franchiseJsons;
    }

    public List<DeveloperJson> getAllDevelopersFromIgdb() {
        logger.info(" ===================== Persisting developerEntities starts. ===================== ");
        final String urlForScroll = "https://api-2445582011268.apicast.io/companies/";
        final String scrollUrlForDevelopers;
        final long requiredRequestsNumb;
        final HttpResponse<DeveloperJson[]> jsonResponse;
        List<DeveloperJson> developerJsons;
        try {
            jsonResponse = getScrollFromIGDB(urlForScroll, getDeveloperFields()).asObject(DeveloperJson[].class);
            scrollUrlForDevelopers = jsonResponse.getHeaders().get("X-Next-Page").get(0);
            requiredRequestsNumb = Math.round(Integer.parseInt(jsonResponse.getHeaders().get("X-Count").get(0)) / 50);
            logger.info(new StringBuilder().append(" Scroll url for requests: ").append(scrollUrlForDevelopers).toString());
            logger.info(new StringBuilder().append(" Got ").append(jsonResponse.getHeaders().get("X-Count").get(0)).append(" developerEntities. ").toString());
            logger.info(new StringBuilder().append(" Doing ").append(requiredRequestsNumb + 1).append(" iterations. ").toString());
            developerJsons = new ArrayList<>(Arrays.asList(jsonResponse.getBody()));
            for (int i = 0; i < requiredRequestsNumb; i++) {
                try {
                    developerJsons.addAll(Arrays.asList(getSetOfObjectsFromIGDB(scrollUrlForDevelopers)
                            .asObject(DeveloperJson[].class)
                            .getBody()));
                } catch (UnirestException e) {
                    logger.info(new StringBuilder().append(" Got to the end of the developerEntities scroll! ").toString());
                }
            }
        } catch (UnirestException e) {
            logger.error(new StringBuilder().append(" Couldnt get the scroll for Developers ").append(e.getMessage()).toString());
            throw new CouldntParseDevelopersException();
        }
        return developerJsons;
    }

    public List<CollectionJson> getAllCollectionsFromIgdb() {
        logger.info(" ===================== Persisting collections starts. ===================== ");
        final String urlForScroll = "https://api-2445582011268.apicast.io/collections/";
        final String scrollUrlForCollections;
        final long requiredRequestsNumb;
        final HttpResponse<CollectionJson[]> jsonResponse;
        List<CollectionJson> collectionJsons;
        try {
            jsonResponse = getScrollFromIGDB(urlForScroll, getBasicFields()).asObject(CollectionJson[].class);
            scrollUrlForCollections = jsonResponse.getHeaders().get("X-Next-Page").get(0);
            requiredRequestsNumb = Math.round(Integer.parseInt(jsonResponse.getHeaders().get("X-Count").get(0)) / 50);
            logger.info(new StringBuilder().append(" Scroll url for requests: ").append(scrollUrlForCollections).toString());
            logger.info(new StringBuilder().append(" Got ").append(jsonResponse.getHeaders().get("X-Count").get(0)).append(" collections. ").toString());
            logger.info(new StringBuilder().append(" Doing ").append(requiredRequestsNumb + 1).append(" iterations. ").toString());
            collectionJsons = new ArrayList<>(Arrays.asList(jsonResponse.getBody()));
            for (int i = 0; i < requiredRequestsNumb; i++) {
                try {
                    collectionJsons.addAll(Arrays.asList(getSetOfObjectsFromIGDB(scrollUrlForCollections)
                            .asObject(CollectionJson[].class)
                            .getBody()));
                } catch (UnirestException e) {
                    logger.info(new StringBuilder().append(" Got to the end of the franchises scroll! ").toString());
                }
            }
        } catch (UnirestException e) {
            logger.error(new StringBuilder().append(" Couldnt get the scroll for collections \n").append(e.getMessage()).toString());
            throw new CouldntParseCollectionsException();
        }
        return collectionJsons;

    }

    public List<GameJson> getAllGamesFromIgdb(){
        logger.info("Persisting games starts.  ");
        List<GameJson> games = null;
        final String urlForScroll = "https://api-2445582011268.apicast.io/games/";
        final String scrollUrlForGames;
        final long requiredRequestsNumb;
        final HttpResponse<GameJson[]> jsonResponse;
        try {
            jsonResponse = getScrollFromIGDB(urlForScroll, getGameFields()).asObject(GameJson[].class);
            scrollUrlForGames = jsonResponse.getHeaders().get("X-Next-Page").get(0);
            requiredRequestsNumb = Math.round(Integer.parseInt(jsonResponse.getHeaders().get("X-Count").get(0)) / 50);
            logger.info(String.format(" Scroll url for requests: %s", scrollUrlForGames));
            logger.info(String.format(" Persisting %s collections. ", jsonResponse.getHeaders().get("X-Count").get(0)));
            logger.info(String.format(" Doing %d iterations. ", requiredRequestsNumb + 1));
            games = new ArrayList<>(Arrays.asList(jsonResponse.getBody()));
            for (int i = 0; i <= requiredRequestsNumb + 1; i++) {
                logger.info(String.format(" Starting iteration numb: %d", i));
                try {
                    games.addAll(Arrays.asList(getSetOfObjectsFromIGDB(scrollUrlForGames)
                            .asObject(GameJson[].class)
                            .getBody()));
                } catch (UnirestException e) {
                    logger.info(new StringBuilder().append(" Got to the end of the games scroll! ").toString());
                }
            }
        } catch (UnirestException e) {
            logger.error(new StringBuilder().append(" Couldnt get the scroll for games ").append(e.getMessage()).toString());
        }
        return games;
    }

    public List<GameJson> getSomeGamesFromIgdb(int numbOfRequests){
        logger.info(" Persisting some games starts.");
        List<GameJson> games;
        final String urlForScroll = "https://api-2445582011268.apicast.io/games/";
        final String scrollUrlForGames;
        final HttpResponse<GameJson[]> jsonResponse;
        try {
            jsonResponse = getScrollFromIGDB(urlForScroll, getGameFields()).asObject(GameJson[].class);
            scrollUrlForGames = jsonResponse.getHeaders().get("X-Next-Page").get(0);
            logger.info(String.format(" Scroll url for requests: %s", scrollUrlForGames));
            logger.info(String.format(" Got %s collections. ", 50 * numbOfRequests + 50));
            games = new ArrayList<>(Arrays.asList(jsonResponse.getBody()));
            for (int i = 0; i < numbOfRequests; i++) {
                try {
                    games.addAll(Arrays.asList(getSetOfObjectsFromIGDB(scrollUrlForGames)
                            .asObject(GameJson[].class)
                            .getBody()));
                } catch (UnirestException e) {
                    logger.info(new StringBuilder().append(" Got to the end of the games scroll! ").toString());
                }
            }
        } catch (UnirestException e) {
            logger.error(" Couldnt get the scroll for games " + e.getMessage() );
            throw new CouldntParseGamesException();
        }
        return games;
    }

    private String getDeveloperFields() {
        return "id," +
                "logo," +
                "name," +
                "url," +
                "description," +
                "website," +
                "start_date";
    }

    private String getBasicFields() {
        return "id," +
                "name," +
                "url," +
                "created_at," +
                "updated_at";
    }

    private String getGameFields() {
        return "id," +
                "name," +
                "slug," +
                "url," +
                "created_at," +
                "updated_at," +
                "summary," +
                "storyline," +
                "first_release_date," +
                "hypes," +
                "popularity," +
                "rating," +
                "rating_count," +
                "aggregated_rating," +
                "aggregated_rating_count," +
                "total_rating," +
                "total_rating_count," +
                "collectionEntity," +
                "franchiseEntity," +
                "time_to_beat," +
                "developerEntities," +
                "game_modes," +
                "genreEntities," +
                "player_perspectives," +
                "websiteEntities," +
                "status," +
                "esrb," +
                "pegi," +
                "websiteEntities," +
                "external," +
                "cover," +
                "screenshots";
    }

    class CouldntParseGenresException extends RuntimeException{}

    class CouldntParseGameModesException extends RuntimeException{}

    class CouldntParsePlayerPerspectivesException extends RuntimeException{}

    class CouldntParseFranchisesException extends RuntimeException{}

    class CouldntParseDevelopersException extends RuntimeException{}

    class CouldntParseCollectionsException extends RuntimeException{}

    class CouldntParseGamesException extends RuntimeException{}

}


