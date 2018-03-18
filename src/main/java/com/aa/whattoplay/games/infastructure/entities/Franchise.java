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
@AllArgsConstructor
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

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
