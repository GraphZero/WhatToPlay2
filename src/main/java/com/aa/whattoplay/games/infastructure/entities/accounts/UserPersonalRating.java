package com.aa.whattoplay.games.infastructure.entities.accounts;

import com.aa.ddd.common.domain.AbstractEntity;
import com.aa.whattoplay.games.domain.suggestions.UserRating;
import com.aa.whattoplay.games.infastructure.entities.igdb.GameEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

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
