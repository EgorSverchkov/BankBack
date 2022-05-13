package com.example.bankbackendapi.controller;


import com.example.bankbackendapi.exception.BadRequestException;
import com.example.bankbackendapi.model.dto.AccountDto;
import com.example.bankbackendapi.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    private final String OPEN_ACCOUNT = "/api/v1/account/open";

    @PostMapping(OPEN_ACCOUNT)
    public ResponseEntity<String> openAccount(@RequestBody AccountDto accountDto){
        try {
            accountService.createAccount(accountDto);
            return ResponseEntity.ok("Account created");
        }
        catch (BadRequestException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    private final String DELETE_ACCOUNT = "/api/v1/account/delete";

    @GetMapping(DELETE_ACCOUNT)
    public ResponseEntity<String> deleteAccount(@RequestParam Long accountNumber){
        try {
            accountService.deleteAccount(accountNumber);
            return ResponseEntity.ok("Account deleted");
        }
        catch (BadRequestException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private final String FREEZE_ACCOUNT = "/api/v1/account/freeze";

    @GetMapping(FREEZE_ACCOUNT)
    public ResponseEntity<String> freezeAccount(@RequestParam Long accountNumber){
        try {
            accountService.freezeAccount(accountNumber);
            return ResponseEntity.ok("Account freezed");
        }
        catch (BadRequestException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private final String UNFREEZE_ACCOUNT = "/api/v1/account/unfreeze";

    @GetMapping(UNFREEZE_ACCOUNT)
    public ResponseEntity<String> unfreezeAccount(@RequestParam Long accountNumber){
        try {
            accountService.unfreezeAccount(accountNumber);
            return ResponseEntity.ok("Account unfreezed");
        }
        catch (BadRequestException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private final String GET_ACCOUNT = "/api/v1/account/get";

    @GetMapping(GET_ACCOUNT)
    public ResponseEntity<AccountDto> getAccount(@RequestParam Long accountNumber){
        try {
            return ResponseEntity.ok().body(accountService.getAccount(accountNumber));
        }
        catch (BadRequestException e){
            return ResponseEntity.badRequest().body(null);
        }
    }
}
