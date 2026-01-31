package com.branko.portfolio.archaeodocs.repository;

import com.branko.portfolio.archaeodocs.domain.Site;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteRepository extends JpaRepository<Site, Long> {
}