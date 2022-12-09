package com.thiagodd.crud_client.domain.service.exceptions;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 2342932093210604168L;

    public ResourceNotFoundException(String message) {
        System.out.println("ENTROU NO ERRO");
    }
}
