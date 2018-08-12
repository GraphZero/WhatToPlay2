package com.aa.whattoplay.games.infastructure.entities.accounts;

import com.aa.ddd.common.domain.AbstractEntity;
import com.aa.whattoplay.games.domain.suggestions.GameEvaluation;
import com.aa.whattoplay.games.domain.suggestions.UserRating;
import com.aa.whattoplay.games.domain.suggestions.value.PlayerPerspective;
import com.aa.whattoplay.games.infastructure.entities.igdb.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "Users_Ratings")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserPersonalRating extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false, referencedColumnName = "id")
    private GameEntity gameEntity;

    @Enumerated(value = EnumType.STRING)
    private UserRating rating;

    public GameEvaluation gameEvaluation() {
        return GameEvaluation
                .builder()
                .collection(gameEntity.getCollectionEntity().value())
                .developer(gameEntity.getDeveloperEntities().stream().map(DeveloperEntity::value).collect(Collectors.toSet()))
                .firstReleaseDate(gameEntity.getFirstReleaseDate())
                .franchise(gameEntity.getFranchiseEntity().value())
                .gameMode(gameEntity.getGameModeEntities().stream().map(GameModeEntity::value).collect(Collectors.toSet()))
                .genre(gameEntity.getGenreEntities().stream().map(GenreEntity::value).collect(Collectors.toSet()))
                .pegi(gameEntity.getPegi())
                .playerPerspective(gameEntity.getPlayerPerspectiveEntities().stream().map(PlayerPerspectiveEntity::value).collect(Collectors.toSet()))
                .popularity(gameEntity.getPopularity())
                .rating(gameEntity.getRating())
                .status(gameEntity.getStatus())
                .userRating(rating)
                .build();
        }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UserPersonalRating)) return false;
        UserPersonalRating userPersonalRating = (UserPersonalRating) obj;
        return Objects.equals(getId(), userPersonalRating.getId());
    }

    @Override
    public String toString() {
        return "UserPersonalRating{" +
                "user=" + user.getId() +
                ", gameEntity=" + gameEntity.getId() +
                ", rating=" + rating +
                '}';
    }
}
