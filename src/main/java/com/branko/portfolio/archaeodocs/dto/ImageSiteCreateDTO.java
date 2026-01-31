package com.branko.portfolio.archaeodocs.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageSiteCreateDTO {

    @NotBlank(message = "filename is required")
    private  String filename;

}
