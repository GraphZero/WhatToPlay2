package com.aa.whattoplay.games.domain.suggestions.value;

import com.aa.ddd.common.annotations.ValueObject;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@ValueObject
@Value
@Builder
public class Genre {
    private long id;
    private String name;
    private String url;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
