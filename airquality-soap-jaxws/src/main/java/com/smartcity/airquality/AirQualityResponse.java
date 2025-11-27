package com.smartcity.airquality;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AirQualityResponse {
    @XmlElement public String zone;
    @XmlElement public int aqi;
    @XmlElement public String level;
    @XmlElement public String mainPollutant;
}