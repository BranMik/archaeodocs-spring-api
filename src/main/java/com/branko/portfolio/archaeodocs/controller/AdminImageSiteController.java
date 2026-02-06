package com.branko.portfolio.archaeodocs.controller;

import com.branko.portfolio.archaeodocs.dto.DeleteImageRequest;
import com.branko.portfolio.archaeodocs.service.ImageSiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/images")
@RequiredArgsConstructor
public class AdminImageSiteController {

    private final ImageSiteService service;

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody DeleteImageRequest req) {
        service.deleteImageByFilename(req.filename());
        return ResponseEntity.noContent().build();
    }
}
