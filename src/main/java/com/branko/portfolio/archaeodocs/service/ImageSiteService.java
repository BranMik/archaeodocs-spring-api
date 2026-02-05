package com.branko.portfolio.archaeodocs.service;

import com.branko.portfolio.archaeodocs.controller.AdminSiteController;
import com.branko.portfolio.archaeodocs.domain.ImageSite;
import com.branko.portfolio.archaeodocs.domain.Site;
import com.branko.portfolio.archaeodocs.dto.ImageSiteCreateDTO;
import com.branko.portfolio.archaeodocs.dto.ImageSiteResponseDTO;
import com.branko.portfolio.archaeodocs.mapper.ImageSiteMapper;
import com.branko.portfolio.archaeodocs.repository.ImageSiteRepository;
import com.branko.portfolio.archaeodocs.repository.SiteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageSiteService {
    private final ImageSiteRepository imageSiteRepo;
    private final SiteRepository siteRepository;
    private final ImageSiteRepository imageSiteRepository;
    private final WebClient webClient;
    private final ImageSiteMapper imageSiteMapper;

    @Value("${files.delete.php.url}")
    private String phpDeleteUrl;

    @Value("${files.delete.token}")
    private String deleteToken;

    private static final Logger log =
            LoggerFactory.getLogger(ImageSiteService.class);

    @Transactional
    public void createImageSiteInDB(Long siteId, ImageSiteCreateDTO dto){
        ImageSite imageSite = new ImageSite();
        imageSite.setFilename(dto.getFilename());

        log.info("in service method");
        Site site = siteRepository.findById(siteId).orElseThrow(() -> new IllegalArgumentException("Site not found: " + siteId));
        site.getImages().add(imageSite);
        log.info("after add before setSite");
        imageSite.setSite(site);
        log.info("after setSite");
        imageSiteRepository.save(imageSite);
        log.info("after save");
    }

    @Transactional(readOnly = true)
    public List<ImageSiteResponseDTO> getAllImages(){
        return imageSiteRepo.findAll()
                .stream()
                .map(imageSiteMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public ImageSiteResponseDTO getImageById(Long imageId){
        return imageSiteRepo.findById(imageId)
                .map(imageSiteMapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Image not found: " + imageId));
    }

    @Transactional
    public void deleteImageByFilename(String path) {
        ImageSite img = imageSiteRepo.findByFilename(path)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        imageSiteRepo.delete(img);

        try {
            webClient.post()
                    .uri(phpDeleteUrl)
                    .header("X-DELETE-TOKEN", deleteToken)
                    .bodyValue(new DeleteFileRequest(path))
                    .retrieve()
                    .toBodilessEntity()
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.MULTI_STATUS);
        }
    }

    public record DeleteFileRequest(String key) {}
}
