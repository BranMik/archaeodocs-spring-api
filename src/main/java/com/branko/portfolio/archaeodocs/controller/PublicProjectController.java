package com.branko.portfolio.archaeodocs.controller;

import com.branko.portfolio.archaeodocs.dto.ProjectResponseDTO;
import com.branko.portfolio.archaeodocs.dto.ProjectSiteRowDTO;
import com.branko.portfolio.archaeodocs.repository.ProjectSiteRepository;
import com.branko.portfolio.archaeodocs.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class PublicProjectController {
    private final ProjectSiteRepository projectSiteRepo;
    private final ProjectService projectService;

    @GetMapping
    public List<ProjectResponseDTO> all() {
        return projectService.getAllProjects();
    }

    @GetMapping("/sites-flat")
    public List<ProjectSiteRowDTO> sitesFlat() {
        return projectSiteRepo.findProjectSitesFlat();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> one(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getOneProjectById(id));
    }
}
