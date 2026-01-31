package com.branko.portfolio.archaeodocs.service;

import com.branko.portfolio.archaeodocs.domain.Project;
import com.branko.portfolio.archaeodocs.dto.ProjectCreateDTO;
import com.branko.portfolio.archaeodocs.dto.ProjectResponseDTO;
import com.branko.portfolio.archaeodocs.dto.ProjectUpdateDTO;
import com.branko.portfolio.archaeodocs.mapper.ProjectMapper;
import com.branko.portfolio.archaeodocs.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper mapper;

    @Transactional
    public ProjectResponseDTO createNewProject(ProjectCreateDTO dto){
        Project project = new Project();
        project.setDescription(dto.getDescription());
        project.setName(dto.getName());
        project.setComment(dto.getComment());

        Project saved = projectRepository.save(project);
        return mapper.toResponseDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<ProjectResponseDTO> getAllProjects(){
        return projectRepository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProjectResponseDTO getOneProjectById(Long id){
        return projectRepository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Project not found: " + id));
    }

    @Transactional
    public ProjectResponseDTO updateProject(Long id, ProjectUpdateDTO updated){
        Project p = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found: " + id));

        p.setName(updated.getName());
        p.setComment(updated.getComment());
        p.setDescription(updated.getDescription());

        return mapper.toResponseDTO(p); // bez save()
    }

    @Transactional
    public ResponseEntity<Void> deleteProject(Long id){
        if (!projectRepository.existsById(id)) return ResponseEntity.notFound().build();
        else {
            projectRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
}
