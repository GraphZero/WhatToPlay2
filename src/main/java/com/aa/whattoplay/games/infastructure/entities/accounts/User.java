package com.aa.whattoplay.games.infastructure.entities.accounts;

import com.aa.ddd.common.domain.AbstractEntity;
import com.aa.whattoplay.games.infastructure.entities.igdb.Game;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Users")
@Getter
public class User extends AbstractEntity {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private List<UserPersonalRating> ratedGames;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }
}
