package com.branko.portfolio.archaeodocs.mapper;

import com.branko.portfolio.archaeodocs.domain.Site;
import com.branko.portfolio.archaeodocs.dto.SiteResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class SiteMapper {
    public SiteResponseDTO toResponseDTO(Site p) {
        return new SiteResponseDTO(
                p.getId(),
                p.getName(),
                p.getComment(),
                p.getDescription()
        );
    }
}
