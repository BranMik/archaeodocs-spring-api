package com.branko.portfolio.archaeodocs.mapper;

import com.branko.portfolio.archaeodocs.domain.Project;
import com.branko.portfolio.archaeodocs.dto.ProjectResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    public ProjectResponseDTO toResponseDTO(Project p) {
        return new ProjectResponseDTO(
                p.getId(),
                p.getName(),
                p.getComment(),
                p.getDescription()
        );
    }
}
