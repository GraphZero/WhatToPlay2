package com.aa.whattoplay.games.domain.igdb.json;

import com.aa.whattoplay.games.infastructure.entities.igdb.CollectionEntity;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CollectionJson {
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
    public void setUpdatedAt(long createdAt) {
        this.updatedAt = Instant.ofEpochMilli(createdAt).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @JsonGetter("created_at")
    public long getCreatedAt() {
        ZonedDateTime zdt = createdAt.atStartOfDay(ZoneId.systemDefault());
        return zdt.toInstant().toEpochMilli();
    }

    @JsonGetter("updated_at")
    public long getUpdatedAt() {
        ZonedDateTime zdt = updatedAt.atStartOfDay(ZoneId.systemDefault());
        return zdt.toInstant().toEpochMilli();
    }

    public CollectionEntity entity(){
        return CollectionEntity.builder()
                .id(id)
                .name(name)
                .url(url)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

}
