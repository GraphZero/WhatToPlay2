package com.aa.whattoplay.games.infastructure.entities.igdb;

import com.aa.whattoplay.games.domain.suggestions.value.Franchise;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "Franchises")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class FranchiseEntity extends IgdbAbstractEntity {

    @Column(name ="name", nullable = false)
    private String name;

    private String url;

    @Column(name ="created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name ="updated_at", nullable = false)
    private LocalDate updatedAt;

    public FranchiseEntity(@NotNull String name, String url, LocalDate createdAt, LocalDate updatedAt) {
        this.name = name;
        this.url = url;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Builder
    public FranchiseEntity(long id, @NotNull String name, String url, LocalDate createdAt, LocalDate updatedAt) {
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

    public Franchise value(){
        return Franchise.builder()
                .id(getId())
                .name(name)
                .url(url)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

}
