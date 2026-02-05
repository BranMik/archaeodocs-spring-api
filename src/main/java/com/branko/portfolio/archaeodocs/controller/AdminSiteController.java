package com.branko.portfolio.archaeodocs.controller;

import com.branko.portfolio.archaeodocs.dto.*;
import com.branko.portfolio.archaeodocs.service.ImageSiteService;
import com.branko.portfolio.archaeodocs.service.SiteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/api/admin/sites")
@RequiredArgsConstructor
@Validated
public class AdminSiteController {
    private final SiteService siteService;
    private final ImageSiteService imageSiteService;
    @Value("${internal.api.key}")
    private String internalKey;

    private static final Logger log =
            LoggerFactory.getLogger(AdminSiteController.class);

    @PostMapping
    public ResponseEntity<SiteResponseDTO> create(@Valid @RequestBody SiteCreateDTO dto) {
        SiteResponseDTO created = siteService.createNewSite(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{siteId}/periods")
    public ResponseEntity<String> updatePeriods(
            @PathVariable("siteId") Long siteId,
            @RequestBody List<String> checkedPeriodNames
    )
    {
        siteService.replacePeriods(siteId,checkedPeriodNames);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patch(
            @PathVariable("id") Long id,
            @RequestBody SitePatchDTO dto
    ) {
        siteService.patchSite(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        siteService.deleteSite(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{siteId}/images")
    public ResponseEntity<?> addImage(
            @RequestHeader("X-Internal-Key") String key,
            @PathVariable Long siteId,
            @Valid @RequestBody ImageSiteCreateDTO dto
    ) {
        log.info("in method");
        if (!internalKey.equals(key)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        log.info("before service call");
        imageSiteService.createImageSiteInDB(siteId, dto);
        log.info("before return");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
