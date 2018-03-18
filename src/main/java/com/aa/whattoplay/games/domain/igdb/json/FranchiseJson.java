package com.aa.whattoplay.games.domain.igdb.json;

import com.aa.whattoplay.games.infastructure.entities.Franchise;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FranchiseJson {
    private long id;
    private String name;
    private String url;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @JsonSetter("created_at")
    public void setCreatedAt(long createdAt) {
        this.createdAt = Instant.ofEpochMilli(createdAt).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @JsonSetter("updated_at")
    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = Instant.ofEpochMilli(updatedAt).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public Franchise entity(){
        return Franchise.builder()
                .id(id)
                .name(name)
                .url(url)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

}
