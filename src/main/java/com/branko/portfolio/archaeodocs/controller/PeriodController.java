package com.branko.portfolio.archaeodocs.controller;

import com.branko.portfolio.archaeodocs.dto.PeriodResponseDTO;
import com.branko.portfolio.archaeodocs.service.PeriodService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/periods")
@AllArgsConstructor
public class PeriodController {

    private final PeriodService periodService;

    @GetMapping
    public ResponseEntity<List<PeriodResponseDTO>> all() {
        return ResponseEntity.ok(periodService.getAllPeriods());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeriodResponseDTO> one(@PathVariable Long id) {
        return ResponseEntity.ok(periodService.getPeriodById(id));
    }

    @GetMapping("/{id}/name")
    public ResponseEntity<String> nameOfOne(@PathVariable Long id) {
        return ResponseEntity.ok(periodService.getOnePeriodName(id));
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllNames() {
        return ResponseEntity.ok(periodService.getAllPeriodNames());
    }
}
