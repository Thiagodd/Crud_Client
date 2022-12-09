package com.thiagodd.crud_client.domain.service.exceptions;

import java.io.Serial;

public class DatabaseIntegratyViolationException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 2739374794603886838L;

    public DatabaseIntegratyViolationException(String message) {
        super(message);
    }
}
