package com.branko.portfolio.archaeodocs.repository;

import com.branko.portfolio.archaeodocs.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
