package com.branko.portfolio.archaeodocs.dto;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "site_id_fk is required")
    private final Long site_id_fk;

}
