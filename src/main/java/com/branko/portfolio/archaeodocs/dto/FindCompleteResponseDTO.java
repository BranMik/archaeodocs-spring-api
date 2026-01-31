package com.branko.portfolio.archaeodocs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FindCompleteResponseDTO {
    private final Long id;
    private final String label;
    private final String comment;
    private final String description;
    private final Long siteId;
    private final String material;
    private final String period;
}
