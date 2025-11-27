package com.smartcity.emergency;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class EmergencyServiceApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(EmergencyServiceApplication.class, args);

        // Démarrage manuel du serveur gRPC (méthode support de cours)
        Server server = ServerBuilder.forPort(9090)
                .addService(new EmergencyGrpcServiceImpl())
                .build();
        server.start();
        System.out.println("gRPC Server started on port 9090");
        server.awaitTermination();
    }
}