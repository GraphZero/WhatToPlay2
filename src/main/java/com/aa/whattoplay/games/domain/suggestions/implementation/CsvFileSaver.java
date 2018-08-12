package com.aa.whattoplay.games.domain.suggestions.implementation;

import com.aa.ddd.common.annotations.DomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

@DomainService
@Slf4j
public class CsvFileSaver {
    private static final String ATTRIBUTES_CSV_FILE = "./storage/csv/";
    private final List<String> gameFields;
    private CSVPrinter csvPrinter;
    private BufferedWriter writer;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public CsvFileSaver(List<String> gameFields) {
        this.gameFields = gameFields;
    }

    private CSVPrinter getCsvPrinter() throws IOException {
        return new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(gameFields.toArray(new String[gameFields.size()])));
    }

    public void saveAttributesToCsvFile(List<Object> attributesToSave, String folderName) throws IOException {
        String completeFolderPath = ATTRIBUTES_CSV_FILE + folderName;
        File file = new File(completeFolderPath);
        if (!file.exists()) {
            Files.createDirectories(Paths.get(completeFolderPath));
            File f = Paths.get(completeFolderPath + "/attributes.csv").toFile();
            FileWriter fw = new FileWriter(f, true);
            this.csvPrinter = new CSVPrinter(fw, CSVFormat.DEFAULT.withHeader(gameFields.toArray(new String[gameFields.size()])));
            csvPrinter.printRecord(pruneNotAllowedCharacters(attributesToSave));
            csvPrinter.flush();
            fw.close();
        } else {
            File f = Paths.get(completeFolderPath + "/attributes.csv").toFile();
            FileWriter fw = new FileWriter(f, true);
            this.csvPrinter = new CSVPrinter(fw, CSVFormat.DEFAULT);
            csvPrinter.printRecord(pruneNotAllowedCharacters(attributesToSave));
            csvPrinter.flush();
            fw.close();
        }
    }

    public List<Object> pruneNotAllowedCharacters(List<Object> attributes) {
        return attributes
                .stream()
                .map(o -> {
                    if (o instanceof String) {
                        String o1 = (String) o;
                        return o1.replaceAll("'", "").replaceAll("&", "");
                    }
                    else return o;
                }).collect(Collectors.toList());
    }

}
