package com.aa.whattoplay.games.infastructure.entities.igdb;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="Collections")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CollectionEntity extends IgdbAbstractEntity {
    @Column(nullable = false)
    @NotNull
    private String name;

    private String url;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public CollectionEntity(@NotNull String name, String url, LocalDate createdAt, LocalDate updatedAt) {
        this.name = name;
        this.url = url;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Builder
    public CollectionEntity(long id, @NotNull String name, String url, LocalDate createdAt, LocalDate updatedAt) {
        super(id);
        this.name = name;
        this.url = url;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CollectionEntity)) return false;
        CollectionEntity that = (CollectionEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(url, that.url) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, url, createdAt, updatedAt);
    }
}
