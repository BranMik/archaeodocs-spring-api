package com.branko.portfolio.archaeodocs.repository;

import com.branko.portfolio.archaeodocs.domain.Find;
import com.branko.portfolio.archaeodocs.repository.projection.FindResponseView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FindRepository extends JpaRepository<Find, Long> {
    @Query("""
    select f.id as id,
         f.label as label,
         s.id as siteId,
         m.label as material,
         p.name as period
    from Find f
    left join f.material m
    left join f.period p
    left join f.site s
    where f.site.id = :siteId
    order by p.startCentury
    """)
    List<FindResponseView> findFindsBySiteId(@Param("siteId") Long siteId);
}
