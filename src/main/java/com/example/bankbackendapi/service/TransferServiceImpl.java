package com.example.bankbackendapi.service;

import com.example.bankbackendapi.exception.BadRequestException;
import com.example.bankbackendapi.model.dto.TransferDto;
import com.example.bankbackendapi.model.entity.TransferEntity;
import com.example.bankbackendapi.repository.TransferRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;


    private final AccountService accountService;

    public TransferServiceImpl(TransferRepository transferRepository, AccountService accountService) {
        this.transferRepository = transferRepository;
        this.accountService = accountService;
    }


    @Override
    public String makeTranser(Long accountNumberFrom, Long accountNumberTo, Double transferAmount) {
        try {
            accountService.withdrawMoneyFromAccountBalance(accountNumberFrom, transferAmount);
        } catch (BadRequestException e){
            throw new BadRequestException("Transfer of funds could not be carried out because such an account does not exist");
        }
        try {
            accountService.addMoneyOnAccountBalance(accountNumberTo, transferAmount);
        } catch (BadRequestException e){
            throw new BadRequestException("Transfer of funds could not be carried out because such an account does not exist");
        }
        transferRepository.save(TransferEntity.builder()
                .accountNumberFrom(accountNumberFrom)
                .accountNumberTo(accountNumberTo)
                .dateOfTransfer(LocalDateTime.now())
                .transferAmount(transferAmount)
                .build());

        return "Transfer of funds completed";
    }

    @Override
    public TransferDto giveInfoAboutTransfer(Long accountNumberFrom, Long accountNumberTo, Double transferAmount) {
        Optional<TransferEntity> byAccountNumberFromAndAccountNumberToAndTransferAmount = transferRepository.findByAccountNumberFromAndAccountNumberToAndTransferAmount(accountNumberFrom, accountNumberTo, transferAmount);
        if(byAccountNumberFromAndAccountNumberToAndTransferAmount.isEmpty()){
            throw new BadRequestException("Fund transfers with these parameters were not found");
        }

        TransferDto response = new TransferDto();
        response.setDateOfTransfer(byAccountNumberFromAndAccountNumberToAndTransferAmount.get().getDateOfTransfer());
        response.setAccountNumberFrom(byAccountNumberFromAndAccountNumberToAndTransferAmount.get().getAccountNumberFrom());
        response.setAccountNumberTo(byAccountNumberFromAndAccountNumberToAndTransferAmount.get().getAccountNumberTo());
        response.setTransferAmount(byAccountNumberFromAndAccountNumberToAndTransferAmount.get().getTransferAmount());

        return response;

    }
}
