package com.branko.portfolio.archaeodocs.mapper;

import com.branko.portfolio.archaeodocs.domain.SiteGeoLocation;
import com.branko.portfolio.archaeodocs.dto.SiteGeoLocationResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class SiteGeoLocationMapper {
    public SiteGeoLocationResponseDTO toResponseDTO(SiteGeoLocation sg) {
        return new SiteGeoLocationResponseDTO(
                sg.getSiteId(),
                sg.getLat(),
                sg.getLng()
        );
    }
}
