package sg.edu.nus.iss.csf_api_weather_server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Weather {
    private String description;
    private double temp;
    private int pressure;
    private int humidity;
    
}
