package com.smartcity.mobility;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")  // autorise le frontend plus tard
public class BusLineController {

    @Autowired
    private BusLineRepository repository;

    // Données de démonstration au démarrage
    @PostConstruct
    public void initData() {
        repository.save(new BusLine(null, "L1", "Centre-Ville → Hôpital", "14:30", true));
        repository.save(new BusLine(null, "L4", "Centre-Ville → Hôpital", "16:30", true));
        repository.save(new BusLine(null, "L9", "Centre-Ville → Port", "14:00", true));
        repository.save(new BusLine(null, "L2", "Gare → Université", "14:45", false));
        repository.save(new BusLine(null, "42", "Aéroport → Port", "15:00", true));
        repository.save(new BusLine(null, "N1", "Ligne de nuit Centre", "00:15", true));
    }

    @GetMapping("/lines")
    public List<BusLine> getAllLines() {
        return repository.findAll();
    }

    @GetMapping("/lines/{id}")
    public BusLine getById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/lines/search")
    public List<BusLine> search(@RequestParam String q) {
        return repository.findByLineNumberContainingIgnoreCase(q);
    }
}