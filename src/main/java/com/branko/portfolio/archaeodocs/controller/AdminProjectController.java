package com.branko.portfolio.archaeodocs.controller;

import com.branko.portfolio.archaeodocs.dto.ProjectCreateDTO;
import com.branko.portfolio.archaeodocs.dto.ProjectResponseDTO;
import com.branko.portfolio.archaeodocs.dto.ProjectUpdateDTO;
import com.branko.portfolio.archaeodocs.service.ProjectService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api/admin/projects")
public class AdminProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> create(@Valid @RequestBody ProjectCreateDTO dto) {
        ProjectResponseDTO created = projectService.createNewProject(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
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
