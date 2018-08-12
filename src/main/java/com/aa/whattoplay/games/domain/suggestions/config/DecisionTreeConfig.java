package com.aa.whattoplay.games.domain.suggestions.config;

import com.aa.whattoplay.games.domain.suggestions.implementation.CsvFileSaver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DecisionTreeConfig {
    private static final List<String> attributes = Arrays.asList("userRating", "popularity", "rating",
            "developer", "gameMode", "genre", "playerPerspective", "firstReleaseDate", "collection", "franchise",
            "status", "pegi");


    static List<String> getAttributesToLearnFrom() {
        return attributes;
    }

    @Bean
    public CsvFileSaver csvFileSaver(){
        return new CsvFileSaver(getAttributesToLearnFrom());
    }
}
