package com.branko.portfolio.archaeodocs.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SiteCreateDTO {

    @NotBlank(message = "Title is required")
    private final String name;
    private final String comment;
    private final String description;

}
