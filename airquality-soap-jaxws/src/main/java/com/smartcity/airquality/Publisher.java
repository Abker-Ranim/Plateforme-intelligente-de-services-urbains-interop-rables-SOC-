package com.smartcity.airquality;

import jakarta.xml.ws.Endpoint;

public class Publisher {
    public static void main(String[] args) {
        String address = "http://0.0.0.0:8082/airquality";
        Endpoint.publish(address, new AirQualityService());
        System.out.println("Service SOAP publié ! WSDL → http://localhost:8082/airquality?wsdl");

        try {
            Thread.currentThread().join();  // bloque indéfiniment → conteneur reste vivant
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}