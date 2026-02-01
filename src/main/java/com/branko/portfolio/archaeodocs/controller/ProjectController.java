package com.branko.portfolio.archaeodocs.controller;

import com.branko.portfolio.archaeodocs.dto.ProjectCreateDTO;
import com.branko.portfolio.archaeodocs.dto.ProjectResponseDTO;
import com.branko.portfolio.archaeodocs.dto.ProjectSiteRowDTO;
import com.branko.portfolio.archaeodocs.dto.ProjectUpdateDTO;
import com.branko.portfolio.archaeodocs.mapper.ProjectMapper;
import com.branko.portfolio.archaeodocs.repository.ProjectRepository;
import com.branko.portfolio.archaeodocs.repository.ProjectSiteRepository;
import com.branko.portfolio.archaeodocs.service.ProjectService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectRepository projectRepo;
    private final ProjectMapper mapper;
    private final ProjectSiteRepository projectSiteRepo;
    private final ProjectService projectService;

    @GetMapping
    public List<ProjectResponseDTO> all() {
        return projectService.getAllProjects();
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> create(@Valid @RequestBody ProjectCreateDTO dto) {
        ProjectResponseDTO created = projectService.createNewProject(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/sites-flat")
    public List<ProjectSiteRowDTO> sitesFlat() {
        return projectSiteRepo.findProjectSitesFlat();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> one(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getOneProjectById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> update(@PathVariable Long id, @RequestBody ProjectUpdateDTO updated) {
        return ResponseEntity.ok(projectService.updateProject(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return projectService.deleteProject(id);
    }
}
