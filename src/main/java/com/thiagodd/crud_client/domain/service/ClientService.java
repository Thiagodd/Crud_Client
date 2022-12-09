package com.thiagodd.crud_client.domain.service;

import com.thiagodd.crud_client.domain.model.Client;
import com.thiagodd.crud_client.domain.model.dto.ClientDto;
import com.thiagodd.crud_client.domain.repository.ClientRepository;
import com.thiagodd.crud_client.domain.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    String MSG_ENTITY_NOT_FOUND = "[CUSTOM] Produto com id %d não foi encontrada!";
    String MSG_ENTITY_IN_USE = "[CUSTOM] Produto com id %d não pode ser deletada, pois está em uso!";

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public Page<ClientDto> findAll(PageRequest pageRequest) {
        Page<Client> clientPage = clientRepository.findAll(pageRequest);
        return clientPage.map(ClientDto::new);
    }

    @Transactional(readOnly = true)
    public ClientDto findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format(MSG_ENTITY_NOT_FOUND, id)));
        return new ClientDto(client);
    }

    @Transactional
    public ClientDto insert(ClientDto clientDto) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDto, client, "id");
        client = clientRepository.save(client);
        return new ClientDto(client);
    }

    @Transactional
    public ClientDto update(Long id, ClientDto clientDto) {
        try {
            Client client = clientRepository.getReferenceById(id);
            BeanUtils.copyProperties(clientDto, client, "id");
            client = clientRepository.save(client);
            return new ClientDto(client);
        } catch (EntityNotFoundException exception) {
            throw new ResourceNotFoundException(String.format(MSG_ENTITY_NOT_FOUND, id));
        }
    }

    public void deleteById(Long id){
        try{
            clientRepository.deleteById(id);
        }catch (EmptyResultDataAccessException exception){
            throw new ResourceNotFoundException(String.format(MSG_ENTITY_NOT_FOUND, id));
        }catch (DataIntegrityViolationException exception){
            throw new DataIntegrityViolationException(String.format(MSG_ENTITY_IN_USE, id));
        }
    }


}
