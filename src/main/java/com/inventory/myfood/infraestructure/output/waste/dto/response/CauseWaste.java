package com.inventory.myfood.infraestructure.output.waste.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CauseWaste {
    private String id;
    private String description;
}
