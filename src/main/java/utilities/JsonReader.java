package utilities;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JsonReader {

    public static JSONObject readJson(String fileName) throws IOException {

        InputStream fis = JsonReader.class.getClassLoader()
                .getResourceAsStream(fileName);

        if (fis == null) {
            throw new IOException("JSON file not found: " + fileName);
        }

        try (fis) {
            String jsonData = new String(
                    fis.readAllBytes(),
                    StandardCharsets.UTF_8
            );

            return new JSONObject(jsonData);
        }
    }
}

