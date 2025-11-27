package com.smartcity.weather;

public record Weather(
        String id,
        String zone,
        int temperature,
        String condition,        // "Ensoleillé", "Pluvieux", etc.
        int humidity,
        int windSpeed,
        double rainProbability,
        String forecastHour      // ex: "14h-16h"
) {}