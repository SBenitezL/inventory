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
    OBJECT_NULL("GC-0006", "ERROR, OBJECT NULL"),
    BAD_FORMAT("GC-0007", "ERROR, BAD FORMAT"),
    ENTITY_NOT_FOUND("GC-0008", "ERROR, COULDN'T FOUND ENTITY"),
    ENTITY_EXISTS("GC-0009", "ERROR, ENTITY ALREADY EXISTS");

    private final String code;
    private final String messageKey;
}
