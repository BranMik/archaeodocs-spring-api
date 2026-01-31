package com.branko.portfolio.archaeodocs.controller;

import com.branko.portfolio.archaeodocs.dto.DeleteImageRequest;
import com.branko.portfolio.archaeodocs.dto.ImageSiteResponseDTO;
import com.branko.portfolio.archaeodocs.mapper.ImageSiteMapper;
import com.branko.portfolio.archaeodocs.repository.ImageSiteRepository;
import com.branko.portfolio.archaeodocs.service.ImageSiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://www.brankomikusic.com")
@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageSiteController {

    private final ImageSiteRepository repo;
    private final ImageSiteMapper mapper;
    private final ImageSiteService service;

    @GetMapping
    public ResponseEntity<List<ImageSiteResponseDTO>> all() {
        return ResponseEntity.ok(service.getAllImages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageSiteResponseDTO> one(@PathVariable Long id) {
        return ResponseEntity.ok(service.getImageById(id));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody DeleteImageRequest req) {
        service.deleteImageByFilename(req.filename());
        return ResponseEntity.noContent().build();
    }
}
