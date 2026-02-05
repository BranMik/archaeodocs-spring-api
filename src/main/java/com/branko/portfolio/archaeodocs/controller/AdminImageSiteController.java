package com.branko.portfolio.archaeodocs.controller;

import com.branko.portfolio.archaeodocs.dto.DeleteImageRequest;
import com.branko.portfolio.archaeodocs.service.ImageSiteService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/images")
@RequiredArgsConstructor
public class AdminImageSiteController {

    private final ImageSiteService service;

    private static final Logger log =
            LoggerFactory.getLogger(AdminImageSiteController.class);

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody DeleteImageRequest req) {
        log.info("Inside delete method");
        service.deleteImageByFilename(req.filename());
        log.info("after service call");
        return ResponseEntity.noContent().build();
    }
}
