package com.aa.whattoplay.games.domain.igdb;

import com.aa.ddd.common.annotations.DomainService;
import com.aa.whattoplay.games.domain.igdb.deserializers.LocalDateDeserializer;
import com.aa.whattoplay.games.domain.igdb.deserializers.TimeToBeatDeserializer;
import com.aa.whattoplay.games.domain.igdb.json.GameJson;
import com.aa.whattoplay.games.domain.igdb.json.TimeToBeatJson;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@DomainService
public class GamesFromJsonFilesLoader implements IGetGamesFromExternalSourceService {
    private static Logger logger = LoggerFactory.getLogger(GamesFromJsonFilesLoader.class);
    private final String filesPath = "games/";
    private final ObjectMapper objectMapper;

    public GamesFromJsonFilesLoader() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        module.addDeserializer(TimeToBeatJson.class, new TimeToBeatDeserializer());
        this.objectMapper = new ObjectMapper()
                .registerModule(module);
    }

    public List<GameJson> getGamesFromFile(int fileNumber){
        List<GameJson> listCar = null;
        try {
            listCar = objectMapper.readValue(new File(filesPath + "gamesPart" + fileNumber + ".json"), new TypeReference<List<GameJson>>(){});
        } catch (IOException e) {
            logger.error("Couldnt get json games because of: \n" + e.getMessage());
        }
        return listCar;
    }

    @Override
    public List<GameJson> getGamesFromExternalSource(){
        List<GameJson> listCar = new ArrayList<>();
        for (int i = 1; i <= new File(filesPath).listFiles().length; i++) {
            listCar.addAll(getGamesFromFile(i));
        }
        return listCar;
    }

}
