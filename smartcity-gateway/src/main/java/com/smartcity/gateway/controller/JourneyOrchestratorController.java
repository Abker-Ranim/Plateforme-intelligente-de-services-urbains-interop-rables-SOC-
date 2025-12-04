package com.smartcity.gateway.controller;

import com.smartcity.gateway.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class JourneyOrchestratorController {

    private final WebClient webClient = WebClient.create("http://localhost:8088");

    @Bean
    public RouterFunction<ServerResponse> journeyRoute() {
        return route().GET("/api/journey/plan", request -> {
            String from = request.queryParam("from").orElse("Inconnu");
            String to = request.queryParam("to").orElse("Inconnu");

            Mono<AirQualityResponse> airQuality = callAirQuality(to);
            Mono<WeatherResponse> weather = callWeather(to);
            Mono<List<BusLine>> busLines = callBusLines(from, to);

            return Mono.zip(airQuality, weather, busLines)
                    .map(tuple -> {
                        AirQualityResponse air = tuple.getT1();
                        WeatherResponse w = tuple.getT2();
                        List<BusLine> lines = tuple.getT3();

                        String recommendation = buildRecommendation(air, w, lines);
                        String emergencyStatus = "Aucune urgence détectée (gRPC non intégré dans le workflow HTTP)";

                        return new JourneyResponse(from, to, air, w, lines, recommendation, emergencyStatus);
                    })
                    .flatMap(response -> ServerResponse.ok().bodyValue(response));
        }).build();
    }

    // === RECOMMANDATION INTELLIGENTE (Point 2 du sujet : alternative si pollué) ===
    private String buildRecommendation(AirQualityResponse air, WeatherResponse weather, List<BusLine> busLines) {
        StringBuilder rec = new StringBuilder();

        // 1. Qualité de l'air + alternative si polluée
        if (air.aqi() > 150) {
            rec.append("🚫 Qualité de l'air DANGEREUSE (AQI: ").append(air.aqi()).append(" - ").append(air.level()).append(")\n");
            rec.append("   → Évitez absolument tout déplacement non essentiel.\n");
            rec.append("   → Recommandation : Restez à l'intérieur ou utilisez un véhicule climatisé fermé.\n\n");
        } else if (air.aqi() > 100) {
            rec.append("⚠️ Qualité de l'air MAUVAISE (AQI: ").append(air.aqi()).append(" - ").append(air.level()).append(")\n");
            rec.append("   → Risque pour la santé lors d'efforts prolongés à l'extérieur.\n");
            rec.append("   → Recommandation alternative : Privilégiez le métro, le tramway couvert ou un taxi climatisé.\n");
            rec.append("   → Évitez la marche ou le vélo sur ce trajet.\n\n");
        } else if (air.aqi() > 50) {
            rec.append("🟡 Qualité de l'air MODÉRÉE (AQI: ").append(air.aqi()).append(")\n");
            rec.append("   → Acceptable, mais vigilance pour les personnes sensibles.\n\n");
        } else {
            rec.append("🟢 Qualité de l'air BONNE (AQI: ").append(air.aqi()).append(")\n");
            rec.append("   → Aucune restriction particulière.\n\n");
        }

        // 2. Météo
        if (weather.rainProbability() > 70) {
            rec.append("🌧️ Forte probabilité de pluie (").append((int) weather.rainProbability()).append("%)\n");
            rec.append("   → Prenez un parapluie ou un véhicule couvert !" ).append("\n");
        } else if (weather.rainProbability() > 40) {
            rec.append("🌦️ Risque de pluie (").append((int) weather.rainProbability()).append("%)\n");
            rec.append("   → Prévoir un parapluie au cas où.\n");
        }

        if (weather.temperature() > 35) {
            rec.append("☀️ Chaleur intense (").append(weather.temperature()).append("°C)\n");
            rec.append("   → Hydratez-vous régulièrement !\n");
        } else if (weather.temperature() < 5) {
            rec.append("🥶 Froid intense (").append(weather.temperature()).append("°C)\n");
            rec.append("   → Habillez-vous chaudement.\n");
        }

        // 3. Lignes de bus
        if (busLines.isEmpty()) {
            rec.append("\n❌ Aucune ligne de bus directe trouvée entre ").append(busLines.get(0).destination()).append("\n");
            rec.append("   → Alternative : Utilisez le métro, un taxi ou vérifiez les correspondances.\n");
        }

        return rec.toString();
    }

    // === QUALITÉ DE L'AIR (SOAP simulé) ===
    private Mono<AirQualityResponse> callAirQuality(String zone) {
        String soapRequest = """
        <?xml version="1.0" encoding="utf-8"?>
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:air="http://smartcity.com/airquality">
          <soapenv:Header/>
          <soapenv:Body>
            <air:getAirQuality>
              <request>
                <zone>%s</zone>
              </request>
            </air:getAirQuality>
          </soapenv:Body>
        </soapenv:Envelope>
        """.formatted(zone);

        return webClient.post()
                .uri("/airquality")  // ou "/api/airquality/ws/airquality" selon ta route exacte
                .header("Content-Type", "text/xml; charset=utf-8")
                .header("SOAPAction", "")  // ← vide, comme dans ton WSDL
                .bodyValue(soapRequest)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(response -> System.out.println("SOAP Response: " + response)) // ← POUR DEBUG (regarde les logs Docker)
                .map(this::parseSoapResponseRobust)
                .onErrorResume(e -> Mono.just(new AirQualityResponse(zone, 50, "Erreur appel SOAP: " + e.getMessage(), "N/A")));
    }

    // Parsing plus tolérant (gère les namespaces comme ns2:, ns3:, etc.)
    private AirQualityResponse parseSoapResponseRobust(String soapResponse) {
        String zone = null;
        try {
            zone = extractValue(soapResponse, "zone");
            String aqiStr = extractValue(soapResponse, "aqi");
            String level = extractValue(soapResponse, "level");
            String mainPollutant = extractValue(soapResponse, "mainPollutant");

            int aqi = 50;
            try {
                aqi = Integer.parseInt(aqiStr);
            } catch (Exception ignored) {
            }

            return new AirQualityResponse(
                    zone.isBlank() ? "Inconnue" : zone,
                    aqi,
                    level.isBlank() ? "Inconnu" : level,
                    mainPollutant.isBlank() ? "N/A" : mainPollutant
            );
        } catch (Exception e) {
            return new AirQualityResponse(zone, 50, "Erreur parsing SOAP", "N/A");
        }
    }

    // Extracteur tolérant aux namespaces (cherche <zone> ou <*:zone>)
    private String extractValue(String xml, String tag) {
        String pattern = "<[^:]*:" + tag + "[^>]*>(.*?)</[^:]*:" + tag + ">";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher m = p.matcher(xml);
        if (m.find()) {
            return m.group(1);
        }
        // Fallback sans namespace
        pattern = "<" + tag + ">(.*?)</" + tag + ">";
        p = java.util.regex.Pattern.compile(pattern);
        m = p.matcher(xml);
        if (m.find()) {
            return m.group(1);
        }
        return "";
    }

    // === MÉTÉO (GraphQL) ===
    private Mono<WeatherResponse> callWeather(String city) {
        String query = "{\"query\": \"{ allWeather { zone temperature condition rainProbability } }\"}";

        return webClient.post()
                .uri("/api/weather/graphql")
                .header("Content-Type", "application/json")
                .bodyValue(query)
                .retrieve()
                .bodyToMono(GraphQLResponse.class)
                .map(resp -> resp.data().allWeather().stream()
                        .filter(w -> w.zone().toLowerCase().contains(city.toLowerCase()) ||
                                city.toLowerCase().contains(w.zone().toLowerCase()))
                        .findFirst()
                        .orElse(new AllWeather(city, 25, "Inconnu", 0.0))
                )
                .map(w -> new WeatherResponse(w.zone(), w.temperature(), w.condition(), w.rainProbability()));
    }

    // === LIGNES DE BUS (REST) ===
    private Mono<List<BusLine>> callBusLines(String from, String to) {
        return webClient.get()
                .uri("/api/bus/lines")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<BusLine>>() {})
                .map(allLines -> allLines.stream()
                        .filter(line -> {
                            String destination = line.destination() != null ? line.destination().toLowerCase() : "";
                            String fromLower = from.toLowerCase();
                            String toLower = to.toLowerCase();

                            // Filtre strict : la ligne doit contenir À LA FOIS from ET to
                            boolean containsFrom = destination.contains(fromLower);
                            boolean containsTo = destination.contains(toLower);

                            return containsFrom && containsTo;
                        })
                        .toList()
                )
                .defaultIfEmpty(List.of())
                .map(filtered -> {
                    if (filtered.isEmpty()) {
                        BusLine fake = new BusLine(null, "Aucune",
                                "Aucune ligne directe trouvée entre " + from + " et " + to + ". Essayez une correspondance.",
                                "-", false);
                        return List.of(fake);
                    }
                    return filtered;
                });
    }

    // === Records internes GraphQL ===
    record GraphQLResponse(Data data) {}
    record Data(List<AllWeather> allWeather) {}
    record AllWeather(String zone, int temperature, String condition, double rainProbability) {}
}