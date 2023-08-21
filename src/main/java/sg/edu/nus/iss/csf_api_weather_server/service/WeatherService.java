package sg.edu.nus.iss.csf_api_weather_server.service;

import java.io.StringReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.csf_api_weather_server.model.Weather;

@Service
public class WeatherService {

    @Value("${api.key}")
    private String apiKey;

    private static final String apiUrl = "https://api.openweathermap.org/data/2.5/weather";

    public Weather getWeather(String q) throws Exception {

        String url = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("q", q)
                .queryParam("appid", apiKey)
                .toUriString();

        RestTemplate template = new RestTemplate();

        try {
            ResponseEntity<String> result = template.exchange(url, HttpMethod.GET, null, String.class);
            String payload = result.getBody();
            JsonReader reader = Json.createReader(new StringReader(payload));
            JsonObject obj = reader.readObject();

            JsonArray weatherArray = obj.getJsonArray("weather");
            String description = weatherArray.getJsonObject(0).getString("description");

            JsonObject mainObject = obj.getJsonObject("main");
            double temp = mainObject.getJsonNumber("temp").doubleValue();
            int pressure = mainObject.getInt("pressure");
            int humidity = mainObject.getInt("humidity");

            return new Weather(description, temp, pressure, humidity);

        } catch (Exception e) {
            throw new InternalError("Error retrieving weather info");
        }

    }
}
