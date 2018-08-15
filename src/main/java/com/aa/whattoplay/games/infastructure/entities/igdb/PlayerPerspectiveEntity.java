package com.aa.whattoplay.games.infastructure.entities.igdb;

import com.aa.whattoplay.games.domain.suggestions.value.PlayerPerspective;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Player_Perspectives")
@Getter
@Setter
@NoArgsConstructor
public class PlayerPerspectiveEntity extends IgdbAbstractEntity {

    @Column( nullable = false)
    @NotNull
    private String name;

    private String url;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @Builder
    public PlayerPerspectiveEntity(long id, @NotNull long id1, @NotNull String name, String url, LocalDate createdAt, LocalDate updatedAt) {
        super(id);
        this.name = name;
        this.url = url;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerPerspectiveEntity)) return false;
        PlayerPerspectiveEntity that = (PlayerPerspectiveEntity) o;
        return getId() == that.getId() &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name);
    }

    public PlayerPerspective value(){
        return PlayerPerspective.builder()
                .id(getId())
                .name(name)
                .url(url)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

}
