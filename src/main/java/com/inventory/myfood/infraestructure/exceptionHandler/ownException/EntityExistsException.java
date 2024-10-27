package com.inventory.myfood.infraestructure.exceptionHandler.ownException;

import com.inventory.myfood.infraestructure.exceptionHandler.exceptionStructure.ErrorCode;

import lombok.Getter;

@Getter
public class EntityExistsException extends RuntimeException {
  private final String messageKey;
  private final String code;

  public EntityExistsException(ErrorCode code) {
    super(code.getCode());
    this.messageKey = code.getMessageKey();
    this.code = code.getCode();
  }

  public EntityExistsException(final String message) {
    super(message);
    this.messageKey = ErrorCode.ENTITY_EXISTS.getMessageKey();
    this.code = ErrorCode.ENTITY_EXISTS.getCode();
  }
}
