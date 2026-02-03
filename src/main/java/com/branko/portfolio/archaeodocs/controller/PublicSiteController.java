package com.branko.portfolio.archaeodocs.controller;

import com.branko.portfolio.archaeodocs.dto.*;
import com.branko.portfolio.archaeodocs.service.ImageSiteService;
import com.branko.portfolio.archaeodocs.service.SiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sites")
@RequiredArgsConstructor
@Validated
public class PublicSiteController {
    private final SiteService siteService;
    private final ImageSiteService imageSiteService;

    @GetMapping
    public List<SiteResponseDTO> all() {
        return siteService.getAllSites();
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
}
