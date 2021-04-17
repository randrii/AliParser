package service.load;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.ProcessingFailureException;
import lombok.RequiredArgsConstructor;
import model.DealItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DefaultLoaderService implements LoaderService {

    private final ObjectMapper mapper;

    public List<DealItem> loadData(int offset) {

        try {
            InputStream inputStream = retrieveItems(offset);
            String jsonString = stringifyStream(inputStream);
            JsonNode jsonNode = mapper.readTree(jsonString);

            return Arrays.asList(mapper.readValue(jsonNode.get("results").toString(), DealItem[].class));
        } catch (IOException e) {
            throw new ProcessingFailureException("Unable to fetch data. Reason: " + e.getMessage());
        }
    }

    private InputStream retrieveItems(int offset) throws IOException {
        return new URL("https://gpsfront.aliexpress.com/getRecommendingResults.do?callback=" +
                "&widget_id=5547572&platform=pc&phase=1&productIds2Top=&limit=50&offset=" + offset +
                "&postback=aad625f0-d733-4e7e-b142-e2db8cdf2201&_=1618583341000")
                .openConnection()
                .getInputStream();
    }

    private String stringifyStream(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .map(this::replaceInvalidChars)
                .collect(Collectors.joining("\n"));
    }

    private String replaceInvalidChars(String line) {
        return line.replace("/**/(", "")
                .replace(");", "");
    }
}
