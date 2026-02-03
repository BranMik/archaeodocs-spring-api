package com.branko.portfolio.archaeodocs.controller;

import com.branko.portfolio.archaeodocs.dto.MaterialResponseDTO;
import com.branko.portfolio.archaeodocs.service.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/public/materials")
public class PublicMaterialController {

    private final MaterialService materialService;

    @GetMapping
    public ResponseEntity<List<MaterialResponseDTO>> all() {
        return ResponseEntity.ok(materialService.getAllMaterials());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialResponseDTO> one(@PathVariable Long id) {
        return ResponseEntity.ok(materialService.getMaterialById(id));
    }
    @GetMapping("/{id}/label")
    public ResponseEntity<String> labelOfOne(@PathVariable Long id) {
        return ResponseEntity.ok(materialService.getMaterialLabelById(id));
    }

    @GetMapping("/labels")
    public ResponseEntity<List<String>> allLabels() {
        return ResponseEntity.ok(materialService.getAllLabels());
    }
}
