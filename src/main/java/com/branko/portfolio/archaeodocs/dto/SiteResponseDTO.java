package com.branko.portfolio.archaeodocs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SiteResponseDTO {

    private final Long id;
    private final String name;
    private final String comment;
    private final String description;

}
