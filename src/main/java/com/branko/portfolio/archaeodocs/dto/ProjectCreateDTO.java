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
public class ProjectCreateDTO {

    @NotBlank(message = "Title is required")
    private String name;
    private String comment;
    private String description;

}
