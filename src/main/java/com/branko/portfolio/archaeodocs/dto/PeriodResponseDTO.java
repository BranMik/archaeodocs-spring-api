package com.branko.portfolio.archaeodocs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PeriodResponseDTO {

    private final Long id;
    private final String name;
    private final int startCentury;
    private final Integer endCentury;

}
