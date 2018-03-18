package com.aa.whattoplay.games.domain.igdb;

import com.aa.ddd.common.annotations.DomainService;
import com.aa.whattoplay.games.domain.igdb.json.GameJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

@DomainService
public class JsonGameFileCacher {
    private final ObjectMapper objectMapper;
    private static Logger logger = LoggerFactory.getLogger(IgdbGamesJsonRequester.class);

    public JsonGameFileCacher() {
        this.objectMapper = new ObjectMapper();
    }

    public void saveGames(List<GameJson> games ){
        logger.info("Starting saving jsons. ");
        int partNumber = 0;
        for (int i = 1; i < games.size(); i++) {
            if ( i % 1000 == 0){
                partNumber++;
                logger.info("Starting creating file number: " + partNumber);
                try {
                    objectMapper.writeValue(new File("./games/games" + "Part" + partNumber + ".json"), games.subList(i - 1000, i));
                } catch (IOException e) {
                    logger.error("Coudlnt save game numb" + i + " to local json file. Number of part: " + partNumber);
                }
            }
        }
    }

}
