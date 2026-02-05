package com.branko.portfolio.archaeodocs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FindCreateDTO {

    @NotBlank(message = "label is required")
    private final String label;
    @NotNull(message = "siteId is required")
    @Positive(message = "siteId must be positive")
    private final Long siteId;
    private final String material;
    private final String period;

}
