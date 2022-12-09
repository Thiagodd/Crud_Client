package com.thiagodd.crud_client.api.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
public class StandardError implements Serializable {

    @Serial
    private static final long serialVersionUID = -1183271732609283785L;
    private Instant timestamp;
    private Integer status;
    private String error;
    private String messege;
    private String path;
}
