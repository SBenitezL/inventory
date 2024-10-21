package com.inventory.myfood.infraestructure.exceptionHandler.exceptionStructure;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    GENERIC_ERROR("GC-0001", "GENERIC ERROR"),
    OBJECT_EXISTS("GC-0002", "ERROR, OBJECT ALREADY EXISTS"),
    OBJECT_NOT_FOUND("GC-0003", "ERROR, COULDN'T FOUND OBJECT"),
    BUSINESS_RULE_VIOLATION("GC-0004", "ERROR, BUSINESS RULE HAS BEEN VIOLATED"),
    NO_DATA("GC-0005", "ERROR, NO DATA"),
    OBJECT_NULL("GC-0006", "ERROR, OBJECT NULL");

    private final String code;
    private final String messageKey;
}
