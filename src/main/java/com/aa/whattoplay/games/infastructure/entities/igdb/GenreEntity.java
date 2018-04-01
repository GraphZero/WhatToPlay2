package com.aa.whattoplay.games.infastructure.entities.igdb;

import com.aa.whattoplay.games.domain.suggestions.value.Genre;
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
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GenreEntity extends IgdbAbstractEntity {
    @Column(nullable = false)
    @NotNull
    private String name;
    private String url;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @Builder
    public GenreEntity(long id, @NotNull long id1, @NotNull String name, String url, LocalDate createdAt, LocalDate updatedAt) {
        super(id);
        this.name = name;
        this.url = url;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenreEntity)) return false;
        GenreEntity genreEntity = (GenreEntity) o;
        return Objects.equals(getId(), genreEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name);
    }

    public Genre value(){
        return Genre.builder()
                .id(getId())
                .name(name)
                .url(url)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

}
