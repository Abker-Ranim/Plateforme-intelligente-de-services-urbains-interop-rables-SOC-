package com.smartcity.weather;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class WeatherController {

    @QueryMapping
    public Weather weather(@Argument String zone) {
        return switch (zone.toLowerCase()) {
            case "centre" -> new Weather("1", "Centre-Ville", 22, "Ensoleillé", 65, 12, 5.0, "14h-16h");
            case "port" -> new Weather("2", "Zone Portuaire", 19, "Nuageux", 80, 25, 60.0, "14h-16h");
            case "hopital" -> new Weather("3", "Hôpital", 20, "Pluvieux", 90, 8, 85.0, "14h-16h");
            default -> new Weather("0", zone, 21, "Variable", 70, 15, 30.0, "Maintenant");
        };
    }

    @QueryMapping
    public List<Weather> allWeather() {
        return List.of(
                new Weather("1", "Centre-Ville", 22, "Ensoleillé", 65, 12, 5.0, "14h-16h"),
                new Weather("2", "Zone Portuaire", 19, "Nuageux", 80, 25, 60.0, "14h-16h"),
                new Weather("3", "Hôpital", 20, "Pluvieux", 90, 8, 85.0, "14h-16h")
        );
    }
}