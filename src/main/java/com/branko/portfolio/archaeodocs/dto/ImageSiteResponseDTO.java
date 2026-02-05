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
public class ImageSiteResponseDTO {

    private final Long id;
    @NotBlank(message = "filename is required")
    private final String filename;
    @NotNull(message = "site_id_fk is required")
    @Positive(message = "site_id_fk must be positive")
    private final Long site_id_fk;

}
