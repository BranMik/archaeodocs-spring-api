package com.branko.portfolio.archaeodocs.service;

import com.branko.portfolio.archaeodocs.domain.Period;
import com.branko.portfolio.archaeodocs.domain.Site;
import com.branko.portfolio.archaeodocs.domain.SiteGeoLocation;
import com.branko.portfolio.archaeodocs.dto.*;
import com.branko.portfolio.archaeodocs.mapper.SiteGeoLocationMapper;
import com.branko.portfolio.archaeodocs.mapper.SiteMapper;
import com.branko.portfolio.archaeodocs.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SiteService {

    private final SiteRepository siteRepo;
    private final SiteGeoLocationRepository siteGeoLocationRepo;
    private final ImageSiteRepository imageSiteRepo;
    private final PeriodRepository periodRepo;
    private final SitePeriodJoinRepository joinRepo;
    private final FindService findService;
    private final SiteMapper siteMapper;
    private final SiteGeoLocationMapper siteGeoLocationMapper;
    private final ProjectSiteRepository projectSiteRepository;

    @Transactional(readOnly = true)
    public List<String> getPeriodsNamesForSite(Long siteId) {
        return joinRepo.findPeriodNamesBySiteId(siteId);
    }

    @Transactional(readOnly = true)
    public List<String> getImagePathsForSite(Long siteId) {
        return imageSiteRepo.findImageFilenamesBySiteId(siteId);
    }

    @Transactional(readOnly = true)
    public List<FindResponseDTO> getFindsForSite(Long siteId) {
        return findService.getFindsBySiteId(siteId);
    }

    @Transactional
    public SiteResponseDTO createNewSite(SiteCreateDTO dto){
        Site site = new Site();
        site.setName(dto.getName());
        site.setDescription(dto.getDescription());

        SiteGeoLocation geo = new SiteGeoLocation();
        geo.setLat(dto.getLat());
        geo.setLng(dto.getLng());

        geo.setSite(site);
        site.setGeoLocation(geo);

        Site saved = siteRepo.save(site);

        projectSiteRepository.insertProjectSite(dto.getProjId(), saved.getId());

        return siteMapper.toResponseDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<SiteGeoLocationResponseDTO> getAllGeoData(){
        return siteGeoLocationRepo.findAll()
                .stream()
                .map(siteGeoLocationMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public SiteResponseDTO getOneSiteById(Long siteId){
        return siteRepo.findById(siteId)
                    .map(siteMapper::toResponseDTO)
                    .orElseThrow(() -> new EntityNotFoundException("Site not found: " + siteId));
    }

    @Transactional(readOnly = true)
    public List<SiteResponseDTO> getAllSites(){
        return siteRepo.findAll()
                    .stream()
                    .map(siteMapper::toResponseDTO)
                    .toList();
    }

    @Transactional
    public void patchSite(Long id, SitePatchDTO dto) {
        Site site = siteRepo.findById(id).orElseThrow();

        if (dto.name() != null) site.setName(dto.name());
        if (dto.description() != null) site.setDescription(dto.description());
        if (dto.comment() != null) site.setComment(dto.comment());
    }

    @Transactional
    public void deleteSite(Long siteId){
        if (!siteRepo.existsById(siteId)) throw new IllegalArgumentException("Site not found: "+siteId);
        siteRepo.deleteById(siteId);
    }

    @Transactional
    public void replacePeriods(long siteId, List<String> checkedPeriodNames) {

        // ensure site exists
        siteRepo.findById(siteId)
                .orElseThrow(() -> new IllegalArgumentException("Site not found: " + siteId));

        // normalize input: remove duplicates/nulls
        List<String> periodNames = checkedPeriodNames == null ? List.of()
                : checkedPeriodNames.stream().filter(Objects::nonNull).distinct().toList();

        List<Period> periods = periodRepo.findByNameIn(checkedPeriodNames);

        // delete all periods added for a site
        joinRepo.deleteJoinRowsForSite(siteId);

        // add all checkedItems/periods back
        for (Period period : periods) {
            joinRepo.insertJoinRow(siteId, period.getId());
        }
    }
}
