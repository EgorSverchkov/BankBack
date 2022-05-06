package com.example.bankbackendapi;

import com.example.bankbackendapi.model.dto.ClientDto;
import com.example.bankbackendapi.service.ClientService;
import com.example.bankbackendapi.service.ClientServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Autowired
    ClientServiceImpl clientService;


    @Test
    void contextLoads() {
    }





    @Test
    void createClientTest(){
        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);
        clientDto.setName("Egor");
        clientDto.setSurname("Sverchkov");
        clientDto.setPhoneNumber("89102962864");
        clientDto.setPassport("1515219759");

        clientService.createClient(clientDto);
    }

    @Test
    void deleteClientTest(){
        Long id = 2L;
        clientService.deleteClient(id);
    }


    @Test
    void updateClientTest(){
        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);
        clientDto.setName("Denis");
        clientDto.setSurname("Shapovalov");
        clientDto.setPhoneNumber("89102962865");
        clientDto.setPassport("1514219759");
        clientService.updateClient(clientDto);
    }
}
