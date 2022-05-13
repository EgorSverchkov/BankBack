package com.example.bankbackendapi.controller;

import com.example.bankbackendapi.exception.BadRequestException;
import com.example.bankbackendapi.model.dto.InformationForMakingTransferDto;
import com.example.bankbackendapi.model.dto.TransferDto;
import com.example.bankbackendapi.service.TransferService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }


    private final String MAKE_TRANSFER = "/api/v1/transfer/make";

    @PostMapping(MAKE_TRANSFER)
    public ResponseEntity<String> makeTransfer(@RequestBody InformationForMakingTransferDto informationForMakingTransferDto) {
        try {
            transferService.makeTranser(informationForMakingTransferDto.getAccountNumberFrom(),
                    informationForMakingTransferDto.getAccountNumberTo(),
                    informationForMakingTransferDto.getTransferAmount());
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Transfer of funds completed");
    }


    private final String GIVE_INFO = "/api/v1/transfer/info";

    @PostMapping(value = GIVE_INFO)
    public ResponseEntity<TransferDto> giveInfoAboutTransfer(@RequestBody InformationForMakingTransferDto informationForMakingTransferDto) {
        try {
            TransferDto transferDto = transferService.giveInfoAboutTransfer(informationForMakingTransferDto.getAccountNumberFrom()
                    , informationForMakingTransferDto.getAccountNumberTo(),
                    informationForMakingTransferDto.getTransferAmount());
            return ResponseEntity.ok(transferDto);

        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
