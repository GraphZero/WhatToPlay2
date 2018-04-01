package com.aa.whattoplay.games.domain.igdb.json;

import com.aa.whattoplay.games.domain.igdb.value.External;
import com.aa.whattoplay.games.domain.igdb.value.Status;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GameJson implements Serializable {
    private long id;
    private String name;
    private String slug;
    private String url;
    private String summary;
    private String storyline;
    private int hypes;
    private double popularity;
    private double rating;
    private int ratingCount;
    private double aggregatedRating;
    private int aggregatedRatingCount;
    private double totalRating;
    private int totalRatingCount;
    private long collectionId;
    private long franchiseId;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate firstReleaseDate;
    private TimeToBeatJson timeToBeat;
    private EsrbJson esrb;
    private PegiJson pegi;
    private Status status;
    private External external;
    private ImageInfoJson cover;
    private List<Long> developersIds;
    private List<Short> playerPerspectivesIds;
    private List<Short> gameModesIds;
    private List<Short> genresIds;
    private List<WebsiteJson> websites;
    private List<ImageInfoJson> screenshots;

    public GameJson(long id, String name, String slug, String url, String summary, String storyline,
                    int hypes, double popularity, double rating, int ratingCount, double aggregatedRating,
                    int aggregatedRatingCount, double totalRating, int totalRatingCount, long collectionId,
                    long franchiseId, LocalDate createdAt, LocalDate updatedAt, LocalDate firstReleaseDate,
                    TimeToBeatJson timeToBeatJson, EsrbJson esrbJson, PegiJson pegiJson, External external, ImageInfoJson cover) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.url = url;
        this.summary = summary;
        this.storyline = storyline;
        this.hypes = hypes;
        this.popularity = popularity;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.aggregatedRating = aggregatedRating;
        this.aggregatedRatingCount = aggregatedRatingCount;
        this.totalRating = totalRating;
        this.totalRatingCount = totalRatingCount;
        this.collectionId = collectionId;
        this.franchiseId = franchiseId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.firstReleaseDate = firstReleaseDate;
        this.timeToBeat = timeToBeatJson;
        this.esrb = esrbJson;
        this.pegi = pegiJson;
        this.external = external;
        this.cover = cover;
        developersIds = new ArrayList<>();
        playerPerspectivesIds = new ArrayList<>();
        gameModesIds = new ArrayList<>();
        genresIds = new ArrayList<>();
        websites = new ArrayList<>();
        screenshots = new ArrayList<>();
        status = Status.RELEASED;
    }

    @JsonSetter("created_at")
    public void setCreatedAt(long createdAt) {
        this.createdAt = Instant.ofEpochMilli(createdAt).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @JsonSetter("first_release_date")
    public void setFirstReleaseDate(long firstReleaseDate) {
        this.firstReleaseDate = Instant.ofEpochMilli(firstReleaseDate).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @JsonSetter("updated_at")
    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = Instant.ofEpochMilli(updatedAt).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @JsonGetter("created_at")
    public long getCreatedAt() {
        return Optional.ofNullable(createdAt).map(createdAt -> {
            ZonedDateTime zdt = createdAt.atStartOfDay(ZoneId.systemDefault());
            return zdt.toInstant().toEpochMilli();
            })
            .orElse((long) 0);
    }

    @JsonGetter("updated_at")
    public long getUpdatedAt() {
        return Optional.ofNullable(updatedAt).map(createdAt -> {
            ZonedDateTime zdt = createdAt.atStartOfDay(ZoneId.systemDefault());
            return zdt.toInstant().toEpochMilli();
            })
            .orElse((long) 0);
    }

    @JsonGetter("first_release_date")
    public long getFirstReleaseDate() {
        return Optional.ofNullable(firstReleaseDate).map(createdAt -> {
            ZonedDateTime zdt = createdAt.atStartOfDay(ZoneId.systemDefault());
            return zdt.toInstant().toEpochMilli();
            })
            .orElse((long) 0);
    }

    @JsonGetter("rating_count")
    public int getRatingCount() {
        return ratingCount;
    }

    @JsonSetter("rating_count")
    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    @JsonGetter("aggregated_rating")
    public double getAggregatedRating() {
        return aggregatedRating;
    }

    @JsonSetter("aggregated_rating")
    public void setAggregatedRating(double aggregatedRating) {
        this.aggregatedRating = aggregatedRating;
    }

    @JsonGetter("aggregated_rating_count")
    public int getAggregatedRatingCount() {
        return aggregatedRatingCount;
    }

    @JsonSetter("aggregated_rating_count")
    public void setAggregatedRatingCount(int aggregatedRatingCount) {
        this.aggregatedRatingCount = aggregatedRatingCount;
    }

    @JsonGetter("total_rating")
    public double getTotalRating() {
        return totalRating;
    }

    @JsonSetter("total_rating")
    public void setTotalRating(double totalRating) {
        this.totalRating = totalRating;
    }

    @JsonGetter("total_rating_count")
    public int getTotalRatingCount() {
        return totalRatingCount;
    }

    @JsonSetter("total_rating_count")
    public void setTotalRatingCount(int totalRatingCount) {
        this.totalRatingCount = totalRatingCount;
    }

    @JsonGetter("collectionEntity")
    public long getCollectionId() {
        return collectionId;
    }

    @JsonSetter("collectionEntity")
    public void setCollectionId(long collectionId) {
        this.collectionId = collectionId;
    }

    @JsonGetter("franchiseEntity")
    public long getFranchiseId() {
        return franchiseId;
    }

    @JsonSetter("franchiseEntity")
    public void setFranchiseId(long franchiseId) {
        this.franchiseId = franchiseId;
    }

    @JsonGetter("developerEntities")
    public List<Long> getDevelopersIds() {
        return developersIds;
    }

    @JsonSetter("developerEntities")
    public void setDevelopersIds(List<Long> developersIds) {
        this.developersIds = developersIds;
    }

    @JsonGetter("time_to_beat")
    public TimeToBeatJson getTimeToBeat() {
        return timeToBeat;
    }

    @JsonSetter("time_to_beat")
    public void setTimeToBeat(TimeToBeatJson timeToBeat) {
        this.timeToBeat = timeToBeat;
    }

    @JsonGetter("player_perspectives")
    public List<Short> getPlayerPerspectivesIds() {
        return playerPerspectivesIds;
    }

    @JsonSetter("player_perspectives")
    public void setPlayerPerspectivesIds(List<Short> playerPerspectivesIds) {
        this.playerPerspectivesIds = playerPerspectivesIds;
    }

    @JsonGetter("game_modes")
    public List<Short> getGameModesIds() {
        return gameModesIds;
    }

    @JsonSetter("game_modes")
    public void setGameModesIds(List<Short> gameModesIds) {
        this.gameModesIds = gameModesIds;
    }

    @JsonGetter("genreEntities")
    public List<Short> getGenresIds() {
        return genresIds;
    }

    @JsonSetter("genreEntities")
    public void setGenresIds(List<Short> genresIds) {
        this.genresIds = genresIds;
    }

    @JsonGetter("status")
    public Status getStatus() {
        return status;
    }

//    @JsonSetter("status")
//    public void setStatus(int status) {
//        for (Status e : Status.values()) {
//            if (e.getGameStatus() == status) {
//                this.status = e;
//                return;
//            }
//        }
//        this.status = Status.RELEASED;
//    }

    @JsonSetter("status")
    public void setStatus(String status) {
        for (Status e : Status.values()) {
            if (e.equals(status)) {
                this.status = e;
                return;
            }
        }
        this.status = Status.RELEASED;
    }

}