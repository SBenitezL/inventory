package com.inventory.myfood.infraestructure.exceptionHandler.ownException;

import com.inventory.myfood.infraestructure.exceptionHandler.exceptionStructure.ErrorCode;

import lombok.Getter;

@Getter
public class ObjectExistsException extends RuntimeException {
    private final String messageKey;
    private final String code;

    public ObjectExistsException(ErrorCode code) {
        super(code.getCode());
        this.messageKey = code.getMessageKey();
        this.code = code.getCode();
    }

    public ObjectExistsException(final String message) {
        super(message);
        this.messageKey = ErrorCode.OBJECT_EXISTS.getMessageKey();
        this.code = ErrorCode.OBJECT_EXISTS.getCode();
    }
}
