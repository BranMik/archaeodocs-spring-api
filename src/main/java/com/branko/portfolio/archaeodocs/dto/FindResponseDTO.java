package com.branko.portfolio.archaeodocs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FindResponseDTO {
    private final Long id;
    private final String label;
    private final Long siteId;
    private final String material;
    private final String period;
}
