package com.smartcity.gateway;

public record WeatherResponse(
        String zone,
        int temperature,
        String condition    ,
        double rainProbability
) {}
