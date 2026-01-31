package com.branko.portfolio.archaeodocs.mapper;

import com.branko.portfolio.archaeodocs.domain.ImageSite;
import com.branko.portfolio.archaeodocs.dto.ImageSiteResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ImageSiteMapper {
    public ImageSiteResponseDTO toResponseDTO(ImageSite is) {
        return new ImageSiteResponseDTO(
                is.getId(),
                is.getFilename(),
                is.getSite().getId()
        );
    }
}
