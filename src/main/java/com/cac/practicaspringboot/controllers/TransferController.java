package com.cac.practicaspringboot.controllers;

import com.cac.practicaspringboot.models.DTOs.AccountDTO;
import com.cac.practicaspringboot.models.DTOs.TransferDTO;
import com.cac.practicaspringboot.services.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TransferDTO>> getTransfers() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getTransfers());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TransferDTO> getTransferById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getTransferById(id));
    }

    @PostMapping
    public ResponseEntity<TransferDTO> createTransfer(@RequestBody TransferDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTransfer(dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteTransfer(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteTransfer(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TransferDTO> updateAllTransfer(@PathVariable Long id, @RequestBody TransferDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAllTransfer(id, dto));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<TransferDTO> updateTransfer(@PathVariable Long id, @RequestBody TransferDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateTransfer(id, dto));
    }
}
