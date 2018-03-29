package com.aa.whattoplay.games.domain.igdb;

import com.aa.ddd.common.annotations.DomainService;
import com.aa.whattoplay.games.domain.igdb.deserializers.LocalDateDeserializer;
import com.aa.whattoplay.games.domain.igdb.deserializers.TimeToBeatDeserializer;
import com.aa.whattoplay.games.domain.igdb.json.TimeToBeatJson;
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
public class JsonFilesManager {
    private static Logger logger = LoggerFactory.getLogger(JsonFilesManager.class);
    private final ObjectMapper objectMapper;

    public JsonFilesManager() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        module.addDeserializer(TimeToBeatJson.class, new TimeToBeatDeserializer());
        this.objectMapper = new ObjectMapper()
                .registerModule(module);
    }

    public void saveJsonToDefaultPath(List<?> collection) {
        String className = collection.get(0).getClass().getSimpleName().replaceAll("Json", "").concat("s");
        logger.info("Starting saving " + className + " jsons. ");
        int partNumber = 0;
        for (int i = 0; i < collection.size(); i += 1000) {
            if (i % 1000 == 0) {
                partNumber++;
                logger.info("Starting creating file number: " + partNumber);
            }
            try {
                if (i + 1000 < collection.size()) {
                    objectMapper.writeValue(new File("./" + className + "/" + className + "Part" + partNumber + ".json"), collection.subList(i, i + 1000));
                } else {
                    objectMapper.writeValue(new File("./" + className + "/" + className + "Part" + partNumber + ".json"), collection.subList(i, collection.size() - 1));
                }
            } catch (IOException e) {
                logger.error("Couldn't save " + className + " numb " + i + " to local json file. Number of part: " + partNumber + " " + e.getMessage());
            }
        }
    }

    public void saveJsonToCustomPath(List<?> collection, String filePath) {
        String className = collection.get(0).getClass().getSimpleName().replaceAll("Json", "").concat("s");
        logger.info("Starting saving " + className + " jsons to " + filePath);
        int partNumber = 0;
        for (int i = 0; i < collection.size(); i += 1001) {
            if (i % 1000 == 0) {
                partNumber++;
                logger.info("Starting creating file number: " + partNumber);
            }
            try {
                if (i + 1000 < collection.size()) {
                    objectMapper.writeValue(new File(filePath + "/" + className + "Part" + partNumber + ".json"), collection.subList(i, i + 1000));
                } else {
                    objectMapper.writeValue(new File(filePath + "/" + className + "Part" + partNumber + ".json"), collection.subList(i, collection.size()));
                }
            } catch (IOException e) {
                logger.error("Couldn't save " + className + " numb " + i + " to local json file. Number of part: " + partNumber + " " + e.getMessage());
            }
        }
    }

    public List getAllObjectsFromJsonsFiles(String filesPath, Class clazz) {
        List list = new ArrayList<>();
        for (int i = 1; i <= new File(filesPath).listFiles().length; i++) {
            list.addAll(getObjectsFromFile(filesPath, clazz, i));
        }
        return list;
    }

    public List getObjectsFromFile(String filesPath, Class clazz, int fileNumber) {
        List objectsList = new ArrayList<>();
        String filePath = filesPath + clazz.getSimpleName().replaceAll("Json", "").concat("s") + "Part" + fileNumber + ".json";
        try {
            objectsList = objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            logger.error("Couldnt get json " + filePath + " because of: \n" + e.getMessage());
        }
        return objectsList;
    }

}
