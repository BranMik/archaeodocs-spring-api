package com.branko.portfolio.archaeodocs.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SiteCreateDTO {

    @NotBlank(message = "Name is required")
    private final String name;
    private final String description;
    private final float lat;
    private final float lng;
    private final Long projId;

}
