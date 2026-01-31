
package com.branko.portfolio.archaeodocs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FindUpdateDTO {

    private final String label;
    private final String comment;
    private final String description;
    private final Long material_fk;
    private final Long period_fk;
    private final Long site_fk;

}
