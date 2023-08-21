package sg.edu.nus.iss.csf_api_weather_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.csf_api_weather_server.model.Weather;
import sg.edu.nus.iss.csf_api_weather_server.service.WeatherService;

@RestController
@RequestMapping(path="/api")
@CrossOrigin
public class WeatherController {

    @Autowired
    private WeatherService service;

    @GetMapping(path="/search", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Weather> getWeather(@RequestParam String q) throws Exception {
        // List<Weather> infoList = service.getWeather(q);

        // return ResponseEntity.ok(Json.createArrayBuilder(infoList).build().toString());
        Weather weather = service.getWeather(q);

        if (weather != null) {
            return ResponseEntity.ok(weather);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
