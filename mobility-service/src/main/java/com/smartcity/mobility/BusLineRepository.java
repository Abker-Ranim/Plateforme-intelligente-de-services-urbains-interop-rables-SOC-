package com.smartcity.mobility;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BusLineRepository extends JpaRepository<BusLine, Long> {
    List<BusLine> findByLineNumberContainingIgnoreCase(String number);
}