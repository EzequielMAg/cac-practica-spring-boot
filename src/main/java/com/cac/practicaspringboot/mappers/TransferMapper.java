package com.cac.practicaspringboot.mappers;

import com.cac.practicaspringboot.models.dtos.TransferDTO;
import com.cac.practicaspringboot.models.Transfer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransferMapper {
    public static Transfer dtoToTransfer(TransferDTO dto) {
        return Transfer.builder()
                .amount(dto.getAmount())
                .originAccount(dto.getOriginAccount())
                .targetAccount(dto.getTargetAccount())
                .transferDate(dto.getTransferDate())
                .transferTime(dto.getTransferTime())
                .build();
    }

    public static TransferDTO transferToDto(Transfer transfer) {
        return TransferDTO.builder()
                .id(transfer.getId())
                .amount(transfer.getAmount())
                .originAccount(transfer.getOriginAccount())
                .targetAccount(transfer.getTargetAccount())
                .transferDate(transfer.getTransferDate())
                .transferTime(transfer.getTransferTime())
                .build();
    }
}