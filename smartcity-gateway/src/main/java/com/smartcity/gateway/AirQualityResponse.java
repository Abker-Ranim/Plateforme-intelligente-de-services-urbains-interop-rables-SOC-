package com.smartcity.gateway;

public record AirQualityResponse(String zone, int aqi, String level, String mainPollutant) {}
