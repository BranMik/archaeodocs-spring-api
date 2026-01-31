package com.branko.portfolio.archaeodocs.repository;

import com.branko.portfolio.archaeodocs.domain.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SitePeriodJoinRepository extends JpaRepository<Site, Long> {

    @Modifying
    @Query(value = "DELETE FROM sites_periods_joint WHERE site_id_fk = :siteId", nativeQuery = true)
    int deleteJoinRowsForSite(@Param("siteId") long siteId);

    @Modifying
    @Query(value = "INSERT INTO sites_periods_joint(site_id_fk, period_id_fk) VALUES (:siteId, :periodId)", nativeQuery = true)
    int insertJoinRow(@Param("siteId") long siteId, @Param("periodId") long periodId);

    @Query(
            """
    SELECT p.name
    FROM Site s
    JOIN s.periods p
    WHERE s.id = :siteId
    ORDER BY p.startCentury
    """
    )
    List<String> findPeriodNamesBySiteId(@Param("siteId") Long siteId);
}
