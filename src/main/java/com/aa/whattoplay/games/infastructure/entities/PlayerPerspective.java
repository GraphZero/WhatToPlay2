package com.aa.whattoplay.games.infastructure.entities;

import com.aa.ddd.common.domain.AbstractEntity;
import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Player_Perspectives")
public class PlayerPerspective extends AbstractEntity {
    @Id
    @Column(nullable = false)
    @NotNull
    private long id;

    @Column( nullable = false)
    @NotNull
    private String name;

    private String url;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @Builder
    public PlayerPerspective(long id, @NotNull long id1, @NotNull String name, String url, LocalDate createdAt, LocalDate updatedAt) {
        super(id);
        this.id = id1;
        this.name = name;
        this.url = url;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerPerspective)) return false;
        PlayerPerspective that = (PlayerPerspective) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(url, that.url) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, url, createdAt, updatedAt);
    }
}
