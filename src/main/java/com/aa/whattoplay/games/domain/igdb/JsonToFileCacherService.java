package com.aa.whattoplay.games.domain.igdb;

import com.aa.ddd.common.annotations.DomainService;
import com.aa.whattoplay.games.domain.igdb.json.CollectionJson;
import com.aa.whattoplay.games.domain.igdb.json.FranchiseJson;
import com.aa.whattoplay.games.domain.igdb.json.GameJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

@DomainService
public class JsonToFileCacherService {
    private static Logger logger = LoggerFactory.getLogger(JsonToFileCacherService.class);
    private final ObjectMapper objectMapper;

    public JsonToFileCacherService() {
        this.objectMapper = new ObjectMapper();
    }

    public void saveJsonToDefaultPath(List<?> collection){
        String className = collection.get(0).getClass().getSimpleName().replaceAll("Json", "").concat("s");
        logger.info("Starting saving "  + className +  " jsons. ");
        int partNumber = 0;
        for (int i = 0; i < collection.size(); i += 1001 ) {
            if ( i % 1000 == 0){
                partNumber++;
                logger.info("Starting creating file number: " + partNumber);
            }
            try {
                if ( i + 1000 < collection.size() ){
                    objectMapper.writeValue(new File( "./" + className + "/" + className + "Part" + partNumber + ".json"), collection.subList(i, i + 1000));
                } else{
                    objectMapper.writeValue(new File("./" + className + "/" + className + "Part" + partNumber + ".json"), collection.subList(i, collection.size() - 1));
                }
            } catch (IOException e) {
                logger.error("Couldn't save " + className+ " numb " + i + " to local json file. Number of part: " + partNumber + " " + e.getMessage());
            }
        }
    }

    public void saveJsonToCustomPath(List<?> collection, String filePath){
        String className = collection.get(0).getClass().getSimpleName().replaceAll("Json", "").concat("s");
        logger.info("Starting saving "  + className +  " jsons to " + filePath);
        int partNumber = 0;
        for (int i = 0; i < collection.size(); i += 1001 ) {
            if ( i % 1000 == 0){
                partNumber++;
                logger.info("Starting creating file number: " + partNumber);
            }
            try {
                if ( i + 1000 < collection.size() ){
                    objectMapper.writeValue(new File( filePath + "/" + className + "Part" + partNumber + ".json"), collection.subList(i, i + 1000));
                } else{
                    objectMapper.writeValue(new File(filePath + "/" + className + "Part" + partNumber + ".json"), collection.subList(i, collection.size() - 1));
                }
            } catch (IOException e) {
                logger.error("Couldn't save " + className+ " numb " + i + " to local json file. Number of part: " + partNumber + " " + e.getMessage());
            }
        }
    }

}
