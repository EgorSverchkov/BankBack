package com.example.bankbackendapi.service;

import com.example.bankbackendapi.model.dto.ClientDto;
import com.example.bankbackendapi.model.entity.ClientEntity;

public interface ClientService {
    String createClient(ClientDto clientDto);
    String deleteClient(Long id);
    String updateClient(ClientDto clientDto);
    ClientDto getClient(String passport);
}
