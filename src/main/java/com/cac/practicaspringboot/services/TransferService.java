package com.cac.practicaspringboot.services;

import com.cac.practicaspringboot.exceptions.EntityAttributesNullException;
import com.cac.practicaspringboot.exceptions.EntityNotExistsException;
import com.cac.practicaspringboot.exceptions.FatalErrorException;
import com.cac.practicaspringboot.exceptions.InsufficientFoundsException;
import com.cac.practicaspringboot.mappers.TransferMapper;
import com.cac.practicaspringboot.models.Account;
import com.cac.practicaspringboot.models.dtos.TransferDTO;
import com.cac.practicaspringboot.models.Transfer;
import com.cac.practicaspringboot.repositories.AccountRepository;
import com.cac.practicaspringboot.repositories.TransferRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final AccountRepository accountRepository;

    // Inyeccion implicita a traves del constructor, otra forma de inyeccion de dependencias aparte del @Autowired
    public TransferService(TransferRepository transferRepository, AccountRepository accountRepository) {
        this.transferRepository = transferRepository;
        this.accountRepository = accountRepository;
    }

    public List<TransferDTO> getTransfers() {
        List<Transfer> transfers = transferRepository.findAll();

        return transfers.stream()
                .map(TransferMapper::transferToDto)
                .collect(Collectors.toList());
    }

    public TransferDTO getTransferById(Long id) {
        Transfer transfer = transferRepository.findById(id).orElseThrow(() ->
                new EntityNotExistsException("¡La transferencia con id " + id + " no existe!"));
        return TransferMapper.transferToDto(transfer);
    }

    // Metodo reemplazado por performTransfer()
    public TransferDTO createTransfer(TransferDTO dto) {

        // Seteo por default la hora y fecha actual de la transferencia
        dto.setTransferDate(LocalDate.now());
        dto.setTransferTime(LocalTime.now());

        Transfer transferSaved = transferRepository.save(TransferMapper.dtoToTransfer(dto));
        return TransferMapper.transferToDto(transferSaved);
    }

    public String deleteTransfer(Long id) {
        if(transferRepository.existsById(id)) {
            transferRepository.deleteById(id);
            return "¡La transferencia con id " + id + " ha sido eliminada!";
        } else {
            throw new EntityNotExistsException("¡La transferencia con id " + id + " no existe!");
            // return "La transferencia a eliminar elegida no existe!";
        }
    }

    public TransferDTO updateAllTransfer(Long id, TransferDTO dto) {
        // Primero verifico si existe una transferencia con ese id en la BD
        // Y también valido que todos los datos del "dto" no vienen en null
        if(transferRepository.existsById(id) && validateTransferDtoAttributes(dto)) {

            // Consigo el usuario a modificar desde la BD
            Transfer transferToModify = transferRepository.findById(id).get();

            // LOGICA DEL PUT
            transferToModify.setAmount(dto.getAmount());
            transferToModify.setOriginAccount(dto.getOriginAccount());
            transferToModify.setTargetAccount(dto.getTargetAccount());

            // Persistimos la modificacion del transfer en la BD
            Transfer transferModified = transferRepository.save(transferToModify);

            return TransferMapper.transferToDto(transferModified);
        }

        if(!transferRepository.existsById(id) && !validateTransferDtoAttributes(dto))
            throw new FatalErrorException("La transferencia con id " + id +
                    " no existe! Y ademas uno o varios atributos son nulos");

        if(!transferRepository.existsById(id))
            throw new EntityNotExistsException("¡La transferencia con id " + id + " no existe!");

        if(!validateTransferDtoAttributes(dto))
            throw new EntityAttributesNullException("¡Uno o varios de los atributos enviados son nulos!");

        return null;
    }

    public boolean validateTransferDtoAttributes(TransferDTO dto) {
        return dto.getAmount() != null &&
                dto.getOriginAccount() != null &&
                dto.getTargetAccount() != null;
    }

    public TransferDTO updateTransfer(Long id, TransferDTO dto) {
        // Primero verifico si existe una transferencia con ese id en la BD
        if(transferRepository.existsById(id)) {

            // Consigo el transfer a modificar desde la BD
            Transfer transferToModify = transferRepository.findById(id).get();

            // Validar que datos no vienen en null para modificar el objeto traido de la BD
            // LOGICA DEL PATCH|
            if(dto.getAmount() != null)
                transferToModify.setAmount(dto.getAmount());

            if(dto.getOriginAccount() != null)
                transferToModify.setOriginAccount(dto.getOriginAccount());

            if(dto.getTargetAccount() != null)
                transferToModify.setTargetAccount(dto.getTargetAccount());

            // Persistimos la modificacion del transfer en la BD
            Transfer transferModified = transferRepository.save(transferToModify);

            return TransferMapper.transferToDto(transferModified);
        }
        throw new EntityNotExistsException("La transferencia con id " + id + " no existe!");
    }

    @Transactional
    public TransferDTO performTransfer(TransferDTO dto) {
        // Comprobar si las cuentas de origen y destino existen
        Account originAccount = accountRepository.findById(dto.getOriginAccount())
                .orElseThrow(() -> new EntityNotExistsException("La cuenta con id " + dto.getOriginAccount() + " no existe!"));

        Account destinationAccount = accountRepository.findById(dto.getTargetAccount())
                .orElseThrow(() -> new EntityNotExistsException("La cuenta con id " + dto.getOriginAccount() + " no existe!"));

        // Comprobar si la cuenta de origen tiene fondos suficientes
        if(originAccount.getAmount().compareTo(dto.getAmount()) < 0) {
            throw new InsufficientFoundsException("Saldo insuficiente en la cuenta con id: " + originAccount.getId());
        }

        // Realizar la transferencia
        originAccount.setAmount(originAccount.getAmount().subtract(dto.getAmount()));
        destinationAccount.setAmount(destinationAccount.getAmount().add(dto.getAmount()));

        // Guardar las cuentas actualizadas
        accountRepository.save(originAccount);
        accountRepository.save(destinationAccount);

        //TODO: refactor, se puede hacer mas corto simplemente agregando la fecha al dto y usando el dtoToTransfer del TransferMapper
        // Crear la transferencia y guardarla en la BD
        Transfer transfer = Transfer.builder()
                .amount(dto.getAmount())
                .originAccount(dto.getOriginAccount())
                .targetAccount(dto.getTargetAccount())
                .transferDate(LocalDate.now())
                .transferTime(LocalTime.now())
                .build();

        transfer = transferRepository.save(transfer);

        // Devolver el DTO de la transferencia realizada
        return TransferMapper.transferToDto(transfer);
    }
}
