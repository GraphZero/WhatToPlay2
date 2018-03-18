package com.aa.whattoplay.games.infastructure.entities;

import com.aa.ddd.common.domain.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "Franchises")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Franchise extends AbstractEntity {
    @Column(name ="name", nullable = false)
    @NotNull
    private String name;

    @Column(name ="url", nullable = true)
    private String url;

    @Column(name ="created_at", nullable = true)
    private LocalDate createdAt;

    @Column(name ="updated_at", nullable = true)
    private LocalDate updatedAt;

    public Franchise(@NotNull String name, String url, LocalDate createdAt, LocalDate updatedAt) {
        this.name = name;
        this.url = url;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Builder
    public Franchise(long id, @NotNull String name, String url, LocalDate createdAt, LocalDate updatedAt) {
        super(id);
        this.name = name;
        this.url = url;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
