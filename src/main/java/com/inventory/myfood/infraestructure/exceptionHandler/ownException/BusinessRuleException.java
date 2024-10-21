package com.inventory.myfood.infraestructure.exceptionHandler.ownException;

import com.inventory.myfood.infraestructure.exceptionHandler.exceptionStructure.ErrorCode;

import lombok.Getter;

@Getter
public class BusinessRuleException extends RuntimeException {

    private final String messageKey;
    private final String code;

    public BusinessRuleException(ErrorCode code) {
        super(code.getCode());
        this.messageKey = code.getMessageKey();
        this.code = code.getCode();
    }

    public BusinessRuleException(final String message) {
        super(message);
        this.messageKey = ErrorCode.BUSINESS_RULE_VIOLATION.getMessageKey();
        this.code = ErrorCode.BUSINESS_RULE_VIOLATION.getCode();
    }
}
