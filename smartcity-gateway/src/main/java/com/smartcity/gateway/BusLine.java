package com.smartcity.gateway;

public record BusLine(
        Long id,
        String lineNumber,
        String destination,
        String nextDeparture,
        boolean onTime
) {}