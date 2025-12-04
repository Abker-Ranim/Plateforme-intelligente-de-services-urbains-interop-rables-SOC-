package com.smartcity.gateway;

import java.util.List;

public record JourneyResponse(
        String from,
        String to,
        AirQualityResponse airQuality,
        WeatherResponse weather,
        List<BusLine> busLines,
        String recommendation,
        String emergencyStatus
) {}