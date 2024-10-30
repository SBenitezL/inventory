package com.inventory.myfood.infraestructure.exceptionHandler.ownException;

import com.inventory.myfood.infraestructure.exceptionHandler.exceptionStructure.ErrorCode;

import lombok.Getter;

@Getter
public class ConectionErrorException extends RuntimeException {
    private final String messageKey;
    private final String code;

    public ConectionErrorException(ErrorCode code) {
        super(code.getCode());
        this.messageKey = code.getMessageKey();
        this.code = code.getCode();
    }

    public ConectionErrorException(final String message) {
        super(message);
        this.messageKey = ErrorCode.CONECTION_ERROR.getMessageKey();
        this.code = ErrorCode.CONECTION_ERROR.getCode();
    }
}
