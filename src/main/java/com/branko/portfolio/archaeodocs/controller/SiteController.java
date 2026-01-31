package com.branko.portfolio.archaeodocs.controller;

import com.branko.portfolio.archaeodocs.dto.*;
import com.branko.portfolio.archaeodocs.mapper.SiteGeoLocationMapper;
import com.branko.portfolio.archaeodocs.mapper.SiteMapper;
import com.branko.portfolio.archaeodocs.repository.SiteGeoLocationRepository;
import com.branko.portfolio.archaeodocs.repository.SiteRepository;
import com.branko.portfolio.archaeodocs.service.ImageSiteService;
import com.branko.portfolio.archaeodocs.service.SiteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "https://www.brankomikusic.com")
@RestController
@RequestMapping("/api/sites")
@RequiredArgsConstructor
@Validated
public class SiteController {
    private final SiteService siteService;
    private final SiteRepository siteRepo;
    private final SiteMapper siteMapper;
    private final SiteGeoLocationRepository siteGeoLocationRepo;
    private final SiteGeoLocationMapper siteGeoLocationMapper;
    private final ImageSiteService imageSiteService;
    @Value("${internal.api.key}")
    private String internalKey;

    @GetMapping
    public List<SiteResponseDTO> all() {
        return siteService.getAllSites();
    }

    @PostMapping
    public ResponseEntity<SiteResponseDTO> create(@Valid @RequestBody SiteCreateDTO dto) {
        SiteResponseDTO created = siteService.createNewSite(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SiteResponseDTO> one(@PathVariable("id") Long id) {
        return ResponseEntity.ok(siteService.getOneSiteById(id));
    }

    @GetMapping("/geo-data")
    public ResponseEntity<List<SiteGeoLocationResponseDTO>> allGeoData() {
        return ResponseEntity.ok(siteService.getAllGeoData());
    }

    @GetMapping("/{siteId}/periods")
    public ResponseEntity<List<String>> sitePeriods(@PathVariable("siteId") Long siteId) {
        return ResponseEntity.ok(siteService.getPeriodsNamesForSite(siteId));
    }

    @GetMapping("/{siteId}/finds")
    public ResponseEntity<List<FindResponseDTO>> siteFinds(@PathVariable("siteId") Long siteId) {
        return ResponseEntity.ok(siteService.getFindsForSite(siteId));
    }

    @GetMapping("/{siteId}/image-paths")
    public ResponseEntity<List<String>> siteImagePaths(@PathVariable("siteId") Long siteId) {
        return ResponseEntity.ok(siteService.getImagePathsForSite(siteId));
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

    @PostMapping("/api/sites/{siteId}/images")
    public ResponseEntity<?> addImage(
            @RequestHeader("X-Internal-Key") String key,
            @PathVariable Long siteId,
            @Valid @RequestBody ImageSiteCreateDTO dto
    ) {
        if (!internalKey.equals(key)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        imageSiteService.createImageSiteInDB(siteId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
