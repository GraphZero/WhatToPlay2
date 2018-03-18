package com.aa.whattoplay.games.domain.igdb.json;

import com.aa.whattoplay.games.infastructure.entities.Genre;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GenreJson {
    private long id;
    private String name;
    private String url;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @JsonGetter("created_at")
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    @JsonSetter("created_at")
    public void setCreatedAt(long createdAt) {
        this.createdAt = Instant.ofEpochMilli(createdAt).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @JsonGetter("updated_at")
    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    @JsonSetter("updated_at")
    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = Instant.ofEpochMilli(updatedAt).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public Genre entity(){
        return Genre.builder()
                .id(id)
                .name(name)
                .url(url)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

}
