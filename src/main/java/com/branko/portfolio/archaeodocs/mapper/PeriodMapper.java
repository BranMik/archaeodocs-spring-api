package com.branko.portfolio.archaeodocs.mapper;

import com.branko.portfolio.archaeodocs.domain.Period;
import com.branko.portfolio.archaeodocs.domain.Project;
import com.branko.portfolio.archaeodocs.dto.PeriodResponseDTO;
import com.branko.portfolio.archaeodocs.dto.ProjectResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class PeriodMapper {
    public PeriodResponseDTO toResponseDTO(Period p) {
        return new PeriodResponseDTO(
                p.getId(),
                p.getName(),
                p.getStartCentury(),
                p.getEndCentury()
        );
    }
}
