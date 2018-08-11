package com.aa.whattoplay.games.domain.suggestions.implementation;

import com.aa.ddd.common.annotations.DomainService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@DomainService
public class CsvFileSaver {
    private static final String ATTRIBUTES_CSV_FILE = "./storage/csv/attributes.csv";
    private final List<String> gameFields;
    private final BufferedWriter writer;
    private final CSVPrinter csvPrinter;

    public CsvFileSaver(List<String> gameFields) throws IOException {
        this.gameFields = gameFields;
        this.writer = Files.newBufferedWriter(Paths.get(ATTRIBUTES_CSV_FILE));
        this.csvPrinter = getCsvPrinter();
    }

    private CSVPrinter getCsvPrinter() throws IOException {
        return new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(gameFields.toArray(new String[gameFields.size()])));
    }

    public void saveAttributesToCsvFile(List<Object> attributesToSave) throws IOException {
        csvPrinter.printRecord(attributesToSave);
        csvPrinter.flush();
    }

}
