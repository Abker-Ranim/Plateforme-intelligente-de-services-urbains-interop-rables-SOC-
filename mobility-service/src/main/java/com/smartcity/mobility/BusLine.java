package com.smartcity.mobility;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusLine {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String lineNumber;      // ex: "L1", "42"

    @NotBlank
    private String destination;    // ex: "Gare → Université"

    private String nextDeparture;   // ex: "14:30"

    private boolean onTime = true;  // true = à l'heure, false = retard
}