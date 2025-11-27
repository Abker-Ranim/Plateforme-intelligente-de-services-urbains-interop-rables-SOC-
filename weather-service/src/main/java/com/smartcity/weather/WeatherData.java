package com.smartcity.weather;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WeatherData {

    private final Map<String, Weather> weathers = new ConcurrentHashMap<>();

    public WeatherData() {
        weathers.put("centre", new Weather("1", "Centre-Ville", 22, "Ensoleillé", 65, 12, 5.0, "14h-16h"));
        weathers.put("port", new Weather("2", "Zone Portuaire", 19, "Nuageux", 80, 25, 60.0, "14h-16h"));
        weathers.put("hopital", new Weather("3", "Quartier Hôpital", 20, "Pluvieux", 90, 8, 85.0, "14h-16h"));
        weathers.put("universite", new Weather("4", "Campus Universitaire", 24, "Ensoleillé", 55, 15, 0.0, "14h-16h"));
    }

    public Weather getByZone(String zone) {
        return weathers.getOrDefault(zone.toLowerCase(),
                new Weather("unknown", zone, 20, "Inconnu", 70, 10, 30.0, "Maintenant"));
    }

    public List<Weather> getAll() {
        return weathers.values().stream().toList();
    }
}