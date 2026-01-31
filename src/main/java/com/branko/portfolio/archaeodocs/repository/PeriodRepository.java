package com.branko.portfolio.archaeodocs.repository;

import com.branko.portfolio.archaeodocs.domain.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface PeriodRepository extends JpaRepository<Period, Long> {
    List<Period> findByNameIn(Collection<String> names);
    List<Period> findByName(String name);
    @Query("select p.name from Period p")
    List<String> findAllNames();
}
