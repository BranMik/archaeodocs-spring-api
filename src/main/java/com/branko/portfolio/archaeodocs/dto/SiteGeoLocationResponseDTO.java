package com.branko.portfolio.archaeodocs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SiteGeoLocationResponseDTO {
        Long siteId;
        Float lat;
        Float lng;
}
