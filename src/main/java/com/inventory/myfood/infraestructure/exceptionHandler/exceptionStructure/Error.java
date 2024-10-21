package com.inventory.myfood.infraestructure.exceptionHandler.exceptionStructure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Error {
    private String errorCode;

    private String message;

    private Integer httpCode;

    @Accessors(chain = true)
    private String url;

    @Accessors(chain = true)
    private String method;
}
