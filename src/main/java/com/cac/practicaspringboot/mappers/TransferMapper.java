package com.cac.practicaspringboot.mappers;

import com.cac.practicaspringboot.models.DTOs.TransferDTO;
import com.cac.practicaspringboot.models.Transfer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransferMapper {
    public static Transfer dtoToTransfer(TransferDTO dto) {
        Transfer transfer = new Transfer();
        transfer.setAmount(dto.getAmount());
        transfer.setSourceAccount(dto.getSourceAccount());
        transfer.setTargetAccount(dto.getTargetAccount());
        transfer.setTransferDate(dto.getTransferDate());
        transfer.setTransferTime(dto.getTransferTime());
        return transfer;
    }

    public static TransferDTO transferToDto(Transfer transfer) {
        TransferDTO dto = new TransferDTO();
        dto.setId(transfer.getId());
        dto.setAmount(transfer.getAmount());
        dto.setSourceAccount(transfer.getSourceAccount());
        dto.setTargetAccount(transfer.getTargetAccount());
        dto.setTransferDate(transfer.getTransferDate());
        dto.setTransferTime(transfer.getTransferTime());
        return dto;
    }
}