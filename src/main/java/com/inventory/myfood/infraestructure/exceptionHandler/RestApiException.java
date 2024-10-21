package com.inventory.myfood.infraestructure.exceptionHandler;

import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.inventory.myfood.infraestructure.exceptionHandler.exceptionStructure.ErrorCode;
import com.inventory.myfood.infraestructure.exceptionHandler.exceptionStructure.ErrorUtils;
import com.inventory.myfood.infraestructure.exceptionHandler.exceptionStructure.Error;
import com.inventory.myfood.infraestructure.exceptionHandler.ownException.BusinessRuleException;
import com.inventory.myfood.infraestructure.exceptionHandler.ownException.NoDataException;
import com.inventory.myfood.infraestructure.exceptionHandler.ownException.ObjectExistsException;
import com.inventory.myfood.infraestructure.exceptionHandler.ownException.ObjectNotFoundException;
import com.inventory.myfood.infraestructure.exceptionHandler.ownException.ObjectNullException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestApiException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleGenericException(final HttpServletRequest req,
            final Exception ex, final Locale locale) {
        final Error error = ErrorUtils
                .createError(ErrorCode.GENERIC_ERROR.getCode(),
                        ErrorCode.GENERIC_ERROR.getMessageKey(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ObjectExistsException.class)
    public ResponseEntity<Error> handleObjectExistscException(final HttpServletRequest req,
            final ObjectExistsException ex) {
        final Error error = ErrorUtils
                .createError(ErrorCode.OBJECT_EXISTS.getCode(),
                        String.format("%s, %s", ErrorCode.OBJECT_EXISTS.getMessageKey(),
                                ex.getMessage()),
                        HttpStatus.NOT_ACCEPTABLE.value())
                .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NoDataException.class)
    public ResponseEntity<Error> handleNoDataException(final HttpServletRequest req,
            final NoDataException ex) {
        final Error error = ErrorUtils
                .createError(ErrorCode.NO_DATA.getCode(),
                        String.format("%s, %s", ErrorCode.NO_DATA.getMessageKey(),
                                ex.getMessage()),
                        HttpStatus.NOT_ACCEPTABLE.value())
                .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Error> handleObjectNotFoundException(final HttpServletRequest req,
            final ObjectNotFoundException ex, final Locale locale) {
        final Error error = ErrorUtils
                .createError(ErrorCode.OBJECT_NOT_FOUND.getCode(),
                        String.format("%s, %s",
                                ErrorCode.OBJECT_NOT_FOUND.getMessageKey(),
                                ex.getMessage()),
                        HttpStatus.NOT_FOUND.value())
                .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<Error> handleBusinessRuleException(final HttpServletRequest req,
            final BusinessRuleException ex, final Locale locale) {
        final Error error = ErrorUtils
                .createError(ErrorCode.BUSINESS_RULE_VIOLATION.getCode(),
                        String.format("%s, %s",
                                ErrorCode.BUSINESS_RULE_VIOLATION.getMessageKey(),
                                ex.getMessage()),
                        HttpStatus.NOT_ACCEPTABLE.value())
                .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjectNullException.class)
    public ResponseEntity<Error> handleObjectNullException(final HttpServletRequest req,
            final ObjectNullException ex, final Locale locale) {
        final Error error = ErrorUtils
                .createError(ErrorCode.OBJECT_NULL.getCode(),
                        String.format("%s, %s",
                                ErrorCode.OBJECT_NULL.getMessageKey(),
                                ex.getMessage()),
                        HttpStatus.NOT_ACCEPTABLE.value())
                .setUrl(req.getRequestURL().toString()).setMethod(req.getMethod());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
