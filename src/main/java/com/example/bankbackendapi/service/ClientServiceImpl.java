package com.example.bankbackendapi.service;

import com.example.bankbackendapi.exception.BadRequestException;
import com.example.bankbackendapi.model.dto.ClientDto;
import com.example.bankbackendapi.model.entity.ClientEntity;
import com.example.bankbackendapi.repository.ClientRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {


    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public String createClient(ClientDto clientDto) {
        if (checkClient(clientDto.getId())) {
            throw new BadRequestException("Client already have");
        }

        clientRepository.save(ClientEntity.builder()
                .id(clientDto.getId())
                .name(clientDto.getName())
                .surname(clientDto.getSurname())
                .phoneNumber(clientDto.getPhoneNumber())
                .passport(clientDto.getPassport())
                .build());

        return "Client created";
    }

    @Override
    public String deleteClient(Long id) {
        if (checkClient(id)) {
            clientRepository.deleteById(id);
            return "Client deleted";
        }
        throw new BadRequestException("Client not found");
    }

    @Override
    public String updateClient(ClientDto newClientDto) {
        if (checkClient(newClientDto.getId())) {
            clientRepository.save(ClientEntity.builder()
                    .id(newClientDto.getId())
                    .name(newClientDto.getName())
                    .surname(newClientDto.getSurname())
                    .phoneNumber(newClientDto.getPhoneNumber())
                    .passport(newClientDto.getPassport())
                    .build());
            return "Client updated";
        }
        throw new BadRequestException("Client not found");

    }




    @Override
    public ClientDto getClient(String passport) {
        if (checkClient(passport)) {
            ClientEntity clientEntity = clientRepository.getByPassport(passport);
            ClientDto clientDto = new ClientDto();
            clientDto.setId(clientEntity.getId());
            clientDto.setName(clientEntity.getName());
            clientDto.setSurname(clientEntity.getSurname());
            clientDto.setPhoneNumber(clientEntity.getPhoneNumber());
            clientDto.setPassport(clientEntity.getPassport());
            return clientDto;


        }
        throw new BadRequestException("Client not found");
    }


    private boolean checkClient(Long id) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(id);

        if (clientEntity.isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean checkClient(String passport){
        Optional<ClientEntity> clientEntity = clientRepository.findByPassport(passport);
        if(clientEntity.isEmpty()){
            return false;
        }
        return true;
    }
}
