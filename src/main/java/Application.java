import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import service.load.DefaultLoaderService;
import service.export.CsvExportService;
import service.export.ExportService;
import service.load.LoaderService;

public class Application {
    public static void main(String[] args) {
        LoaderService defaultLoaderService = new DefaultLoaderService(new ObjectMapper());
        ExportService exportService = new CsvExportService(new CsvMapper());

        exportService.export(defaultLoaderService.loadData(0));
        exportService.export(defaultLoaderService.loadData(50));
        exportService.export(defaultLoaderService.loadData(100));
    }
}
