package com.smartcity.emergency;

import io.grpc.stub.StreamObserver;
import com.smartcity.emergency.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EmergencyGrpcServiceImpl extends EmergencyServiceGrpc.EmergencyServiceImplBase {

    private final AtomicInteger counter = new AtomicInteger();
    // On garde les alertes en mémoire
    private final List<Alert> activeAlerts = new ArrayList<>();

    @Override
    public void sendAlert(AlertRequest request, StreamObserver<AlertResponse> responseObserver) {
        String id = "ALERT-" + counter.incrementAndGet();

        // On crée l'alerte et on la stocke
        Alert alert = Alert.newBuilder()
                .setId(id)
                .setZone(request.getZone())
                .setType(request.getType())
                .setTimestamp(LocalDateTime.now().toString())
                .build();

        activeAlerts.add(alert);  // ← stockage en mémoire

        AlertResponse response = AlertResponse.newBuilder()
                .setId(id)
                .setStatus("REÇUE")
                .setMessage("Alerte " + request.getType() + " à " + request.getZone())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getActiveAlerts(Empty request, StreamObserver<ActiveAlertsResponse> responseObserver) {
        ActiveAlertsResponse response = ActiveAlertsResponse.newBuilder()
                .addAllAlerts(activeAlerts)  // ← on renvoie tout
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}