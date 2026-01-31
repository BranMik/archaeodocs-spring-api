package com.branko.portfolio.archaeodocs.repository;

import com.branko.portfolio.archaeodocs.domain.ImageSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ImageSiteRepository extends JpaRepository<ImageSite, Long> {
    @Query("select i.filename from ImageSite i where i.site.id = :siteId")
    List<String> findImageFilenamesBySiteId(Long siteId);

    Optional<ImageSite> findByFilename(String filename);
}
