package com.branko.portfolio.archaeodocs.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FindCreateDTO {

    @NotBlank(message = "label is required")
    private final String label;
    @NotBlank(message = "siteId is required")
    private final Long siteId;
    private final String material;
    private final String period;

}
