package com.example.bankbackendapi.controller;

import com.example.bankbackendapi.exception.BadRequestException;
import com.example.bankbackendapi.model.dto.ClientDto;
import com.example.bankbackendapi.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    private final String CREATE_CLIENT = "/api/v1/client/create";

    @PostMapping(CREATE_CLIENT)
    public ResponseEntity<String> createClient(@RequestBody ClientDto clientDto) {
        try {
            clientService.createClient(clientDto);
            return ResponseEntity.ok("Client created");
        } catch (BadRequestException badRequestException) {
            return ResponseEntity.badRequest().body(badRequestException.getMessage());
        }

    }

    private final String DELETE_CLIENT = "/api/v1/client/delete";

    @GetMapping(DELETE_CLIENT)
    public ResponseEntity<String> deleteClient(@RequestParam Long id) {

        try {
            clientService.deleteClient(id);
            return ResponseEntity.ok("Client deleted");
        } catch (BadRequestException badRequestException) {
            return ResponseEntity.badRequest().body(badRequestException.getMessage());
        }
    }

    private final String UPDATE_CLIENT = "/api/v1/client/update";

    @PostMapping(UPDATE_CLIENT)
    public ResponseEntity<String> updateClient(@RequestBody ClientDto clientDto) {
        try {
            clientService.updateClient(clientDto);
            return ResponseEntity.ok("Client updated");
        } catch (BadRequestException badRequestException) {
            return ResponseEntity.badRequest().body(badRequestException.getMessage());
        }
    }

    private final String GET_CLIENT_INFO = "/api/v1/client/get_client";

    @GetMapping(GET_CLIENT_INFO)
    public ResponseEntity<ClientDto> getClient(@RequestParam String passport) {
        try {
            ClientDto client = clientService.getClient(passport);
            return ResponseEntity.ok(client);
        } catch (BadRequestException badRequestException) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
