package com.branko.portfolio.archaeodocs.controller;

import com.branko.portfolio.archaeodocs.dto.ImageSiteResponseDTO;
import com.branko.portfolio.archaeodocs.service.ImageSiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/images")
@RequiredArgsConstructor
public class PublicImageSiteController {

    private final ImageSiteService service;

    @GetMapping
    public ResponseEntity<List<ImageSiteResponseDTO>> all() {
        return ResponseEntity.ok(service.getAllImages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageSiteResponseDTO> one(@PathVariable Long id) {
        return ResponseEntity.ok(service.getImageById(id));
    }
}
