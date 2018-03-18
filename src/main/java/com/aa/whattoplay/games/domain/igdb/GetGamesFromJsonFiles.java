package com.aa.whattoplay.games.domain.igdb;

import com.aa.ddd.common.annotations.DomainService;
import com.aa.whattoplay.games.domain.igdb.deserializers.LocalDateDeserializer;
import com.aa.whattoplay.games.domain.igdb.deserializers.TimeToBeatDeserializer;
import com.aa.whattoplay.games.domain.igdb.json.GameJson;
import com.aa.whattoplay.games.domain.igdb.json.TimeToBeatJson;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@DomainService
public class GetGamesFromJsonFiles {
    private final ObjectMapper objectMapper;
    private static Logger logger = LoggerFactory.getLogger(GetGamesFromJsonFiles.class);

    public GetGamesFromJsonFiles() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        module.addDeserializer(TimeToBeatJson.class, new TimeToBeatDeserializer());
        this.objectMapper = new ObjectMapper()
                .registerModule(module);
    }

    public List<GameJson> getGamesFromFiles(){
        List<GameJson> listCar = null;
        try {
            listCar = objectMapper.readValue(new File("games/gamesPart1.json"), new TypeReference<List<GameJson>>(){});
        } catch (IOException e) {
            logger.error("Couldnt get json games because of: \n" + e.getMessage());
        }
        return listCar;
    }





}
