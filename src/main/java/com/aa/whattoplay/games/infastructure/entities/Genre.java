package com.aa.whattoplay.games.infastructure.entities;

import com.aa.ddd.common.domain.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Genres")
@Getter @Setter @ToString  @NoArgsConstructor @AllArgsConstructor
public class Genre extends AbstractEntity {
    @Id
    @Column(nullable = false)
    @NotNull
    private long id;

    @Column(nullable = false)
    @NotNull
    private String name;
    private String url;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        Genre genre = (Genre) o;
        return id == genre.id &&
                Objects.equals(name, genre.name) &&
                Objects.equals(url, genre.url) &&
                Objects.equals(createdAt, genre.createdAt) &&
                Objects.equals(updatedAt, genre.updatedAt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, url, createdAt, updatedAt);
    }
}
