package com.inventory.myfood.infraestructure.exceptionHandler.ownException;

import com.inventory.myfood.infraestructure.exceptionHandler.exceptionStructure.ErrorCode;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {
  private final String messageKey;
  private final String code;

  public EntityNotFoundException(ErrorCode code) {
    super(code.getCode());
    this.messageKey = code.getMessageKey();
    this.code = code.getCode();
  }

  public EntityNotFoundException(final String message) {
    super(message);
    this.messageKey = ErrorCode.ENTITY_NOT_FOUND.getMessageKey();
    this.code = ErrorCode.ENTITY_NOT_FOUND.getCode();
  }
}
