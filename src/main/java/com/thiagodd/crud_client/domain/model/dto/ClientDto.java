package com.thiagodd.crud_client.domain.model.dto;

import com.thiagodd.crud_client.domain.model.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.thiagodd.crud_client.domain.model.Client} entity
 */
@Data
@NoArgsConstructor
public class ClientDto implements Serializable {

    private Long id;
    private String name;
    private String cpf;
    private String income;
    private Instant birthDate;
    private Integer children;

    public ClientDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.income = client.getIncome();
        this.birthDate = client.getBirthDate();
        this.children = client.getChildren();
    }
}