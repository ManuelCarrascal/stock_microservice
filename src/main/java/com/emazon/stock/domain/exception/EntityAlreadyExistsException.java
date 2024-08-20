package com.emazon.stock.domain.exception;

import com.emazon.stock.domain.util.ExceptionMessagesConstants;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String entityName) {
        super(entityName + " " + ExceptionMessagesConstants.ENTITY_ALREADY_EXISTS.getMessage());
    }
}
