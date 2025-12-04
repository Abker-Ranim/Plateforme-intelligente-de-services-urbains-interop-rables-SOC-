package com.smartcity.airquality;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(serviceName = "AirQualityService", targetNamespace = "http://smartcity.com/airquality")
public class AirQualityService {

    @WebMethod(operationName = "getAirQuality")
    public AirQualityResponse getAirQuality(@WebParam(name = "request") AirQualityRequest request) {
        String zone = request.zone.toLowerCase();
        return switch (zone) {
            case "centre" -> new AirQualityResponse() {{
                zone = "Centre-Ville"; aqi = 102; level = "Bon"; mainPollutant = "PM2.5";
            }};
            case "Port" -> new AirQualityResponse() {{
                zone = "Port"; aqi = 20; level = "Bon"; mainPollutant = "PM2.6";
            }};
            case "industriel" -> new AirQualityResponse() {{
                zone = "Zone Industrielle"; aqi = 156; level = "Mauvais"; mainPollutant = "NO2";
            }};
            case "Hôpital" -> new AirQualityResponse() {{
                zone = "Hôpital"; aqi = 160; level = "Mauvais"; mainPollutant = "NO2";
            }};
            default -> new AirQualityResponse() {{
                zone = request.zone; aqi = 35; level = "Bon"; mainPollutant = "PM10";
            }};
        };
    }
}
