package com.branko.portfolio.archaeodocs.repository;

import com.branko.portfolio.archaeodocs.domain.SiteGeoLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteGeoLocationRepository extends JpaRepository<SiteGeoLocation, Long> {
}