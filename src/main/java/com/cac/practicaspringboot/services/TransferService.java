package com.cac.practicaspringboot.services;

import com.cac.practicaspringboot.exceptions.EntityAttributesNullException;
import com.cac.practicaspringboot.exceptions.EntityNotExistsException;
import com.cac.practicaspringboot.exceptions.FatalErrorException;
import com.cac.practicaspringboot.mappers.TransferMapper;
import com.cac.practicaspringboot.models.DTOs.TransferDTO;
import com.cac.practicaspringboot.models.Transfer;
import com.cac.practicaspringboot.repositories.TransferRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {

    private final TransferRepository repository;

    public TransferService(TransferRepository repository) {
        this.repository = repository;
    }

    public List<TransferDTO> getTransfers() {
        List<Transfer> transfers = repository.findAll();

        return transfers.stream()
                .map(TransferMapper::transferToDto)
                .collect(Collectors.toList());
    }

    public TransferDTO getTransferById(Long id) {
        return TransferMapper.transferToDto(repository.findById(id).get());
    }

    public TransferDTO createTransfer(TransferDTO dto) {

        // Seteo por default la hora y fecha actual de la transferencia
        dto.setTransferDate(LocalDate.now());
        dto.setTransferTime(LocalTime.now());

        Transfer transferSaved = repository.save(TransferMapper.dtoToTransfer(dto));
        return TransferMapper.transferToDto(transferSaved);
    }

    public String deleteTransfer(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return "La transferencia con id " + id + " ha sido eliminada!";
        } else {
            throw new EntityNotExistsException("La transferencia a eliminar elegida no existe!");
        }
    }

    public TransferDTO updateAllTransfer(Long id, TransferDTO dto) {
        // Primero verifico si existe una transferencia con ese id en la BD
        // Y tambien valida que todos los datos del "dto" no vienen en null
        if(repository.existsById(id) && validateTransferDtoAttributes(dto)) {

            // Consigo el usuario a modificar desde la BD
            Transfer transferToModify = repository.findById(id).get();

            // LOGICA DEL PUT
            transferToModify.setAmount(dto.getAmount());
            transferToModify.setSourceAccount(dto.getSourceAccount());
            transferToModify.setTargetAccount(dto.getTargetAccount());

            // Persistimos la modificacion del transfer en la BD
            Transfer transferModified = repository.save(transferToModify);

            return TransferMapper.transferToDto(transferModified);
        }

        if(!repository.existsById(id) && !validateTransferDtoAttributes(dto))
            throw new FatalErrorException("La transferencia con id " + id +
                    " no existe! Y ademas uno o varios atributos son nulos");

        if(!repository.existsById(id))
            throw new EntityNotExistsException("La transferencia con id " + id + " no existe!");

        if(!validateTransferDtoAttributes(dto))
            throw new EntityAttributesNullException("Uno o varios de los atributos enviados son nulos");

        return null;
    }

    public boolean validateTransferDtoAttributes(TransferDTO dto) {
        return dto.getAmount() != null &&
                dto.getSourceAccount() != null &&
                dto.getTargetAccount() != null;
    }

    public TransferDTO updateTransfer(Long id, TransferDTO dto) {
        // Primero verifico si existe una transferencia con ese id en la BD
        if(repository.existsById(id)) {

            // Consigo el transfer a modificar desde la BD
            Transfer transferToModify = repository.findById(id).get();

            // Validar que datos no vienen en null para modificar el objeto traido de la BD
            // LOGICA DEL PATCH|
            if(dto.getAmount() != null)
                transferToModify.setAmount(dto.getAmount());

            if(dto.getSourceAccount() != null)
                transferToModify.setSourceAccount(dto.getSourceAccount());

            if(dto.getTargetAccount() != null)
                transferToModify.setTargetAccount(dto.getTargetAccount());

            // Persistimos la modificacion del transfer en la BD
            Transfer transferModified = repository.save(transferToModify);

            return TransferMapper.transferToDto(transferModified);
        }
        throw new EntityNotExistsException("La transferencia con id " + id + " no existe!");
    }
}
