package com.cac.practicaspringboot.controllers;

import com.cac.practicaspringboot.models.dtos.AccountDTO;
import com.cac.practicaspringboot.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAccounts() {
        List<AccountDTO> lista = service.getAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAccountById(id));
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAccount(dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteAccount(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AccountDTO> updateAllAccount(@PathVariable Long id, @RequestBody AccountDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAllAccount(id, dto));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable Long id, @RequestBody AccountDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAccount(id, dto));
    }

}
