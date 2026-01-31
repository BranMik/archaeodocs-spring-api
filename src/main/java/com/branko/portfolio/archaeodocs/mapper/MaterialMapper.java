package com.branko.portfolio.archaeodocs.mapper;

import com.branko.portfolio.archaeodocs.domain.Material;
import com.branko.portfolio.archaeodocs.dto.MaterialResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class MaterialMapper {
    public MaterialResponseDTO toResponseDTO(Material m) {
        return new MaterialResponseDTO(
                m.getId(),
                m.getLabel()
        );
    }
}
