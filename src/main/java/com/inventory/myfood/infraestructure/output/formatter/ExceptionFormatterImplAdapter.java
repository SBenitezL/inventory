package com.inventory.myfood.infraestructure.output.formatter;

import org.springframework.stereotype.Service;

import com.inventory.myfood.application.output.ExceptionFormatterIntPort;
import com.inventory.myfood.infraestructure.exceptionHandler.ownException.BadFormatException;
import com.inventory.myfood.infraestructure.exceptionHandler.ownException.BusinessRuleException;
import com.inventory.myfood.infraestructure.exceptionHandler.ownException.ConectionErrorException;
import com.inventory.myfood.infraestructure.exceptionHandler.ownException.EntityExistsException;
import com.inventory.myfood.infraestructure.exceptionHandler.ownException.EntityNotFoundException;
import com.inventory.myfood.infraestructure.exceptionHandler.ownException.NoDataException;

@Service
public class ExceptionFormatterImplAdapter implements ExceptionFormatterIntPort {
    @Override
    public void returnResponseErrorEntityExists(String message) {
        EntityExistsException objException = new EntityExistsException(message);
        throw objException;
    }

    @Override
    public void returnResponseErrorEntityNotFound(String message) {
        EntityNotFoundException objException = new EntityNotFoundException(message);
        throw objException;
    }

    @Override
    public void returnResponseBusinessRuleViolated(String message) {
        BusinessRuleException objException = new BusinessRuleException(message);
        throw objException;
    }

    @Override
    public void returnResponseBadFormat(String message) {
        BadFormatException objException = new BadFormatException(message);
        throw objException;
    }

    @Override
    public void returNoData(String message) {
        NoDataException objException = new NoDataException(message);
        throw objException;
    }

    @Override
    public void returnCotectionError(String message) {
        ConectionErrorException objException = new ConectionErrorException(message);
        throw objException;
    }
}
