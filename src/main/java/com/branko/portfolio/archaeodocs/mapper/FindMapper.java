package com.branko.portfolio.archaeodocs.mapper;

import com.branko.portfolio.archaeodocs.domain.Find;
import com.branko.portfolio.archaeodocs.dto.FindCompleteResponseDTO;
import com.branko.portfolio.archaeodocs.dto.FindResponseDTO;
import com.branko.portfolio.archaeodocs.repository.projection.FindResponseView;
import org.springframework.stereotype.Component;

@Component
public class FindMapper {
    public FindResponseDTO toResponseDTO(Find f) {
        return new FindResponseDTO(
                f.getId(),
                f.getLabel(),
                f.getSite().getId(),
                f.getMaterial().getLabel(),
                f.getPeriod().getName()
        );
    }
    public FindCompleteResponseDTO toCompleteResponseDTO(Find f) {
        return new FindCompleteResponseDTO(
                f.getId(),
                f.getLabel(),
                f.getComment(),
                f.getDescription(),
                f.getSite().getId(),
                f.getMaterial().getLabel(),
                f.getPeriod().getName()
        );
    }
    public FindResponseDTO fromViewToResponseDTO(FindResponseView v) {
        return new FindResponseDTO(
                v.getId(),
                v.getLabel(),
                v.getSiteId(),
                v.getMaterial(),
                v.getPeriod()
        );
    }
}
