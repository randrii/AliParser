package service.export;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import exception.ProcessingFailureException;
import lombok.RequiredArgsConstructor;
import model.DealItem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@RequiredArgsConstructor
public class CsvExportService implements ExportService {
    private final CsvMapper csvMapper;

    static {
        deleteOutputFile();
    }

    public void export(List<DealItem> items) {
        csvMapper.disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
        CsvSchema csvSchema = csvMapper.schemaFor(DealItem.class).withHeader();
        ObjectWriter writer = csvMapper.writer(csvSchema.withLineSeparator("\n"));

        try {
            writer.writeValue(Files.newBufferedWriter(Paths.get("src/main/resources/flash-deals.csv"),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND), items);
        } catch (IOException e) {
            throw new ProcessingFailureException("Unable to export data. Reason: " + e.getMessage());
        }
    }

    private static void deleteOutputFile() {
        try {
            Files.deleteIfExists(Paths.get("src/main/resources/flash-deals.csv"));
        } catch (IOException e) {
            throw new ProcessingFailureException("Unable to find target output file. Reason: " + e.getMessage());
        }
    }
}