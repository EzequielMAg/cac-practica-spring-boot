package com.cac.practicaspringboot.services;

import com.cac.practicaspringboot.exceptions.EntityNotExistsException;
import com.cac.practicaspringboot.exceptions.FatalErrorException;
import com.cac.practicaspringboot.exceptions.EntityAttributesNullException;
import com.cac.practicaspringboot.mappers.AccountMapper;
import com.cac.practicaspringboot.models.Account;
import com.cac.practicaspringboot.models.dtos.AccountDTO;
import com.cac.practicaspringboot.models.enums.AccountType;
import com.cac.practicaspringboot.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public List<AccountDTO> getAccounts() {
        // Obtengo la lista de la entidad cuentas de la DB
        List<Account> accounts = repository.findAll();

        //Mapea cada cuenta de la lista hacia un DTO generando una lista de dtos la cual terminamos retornan::do
        return accounts.stream()
                .map(AccountMapper::accountToDto)
                .collect(Collectors.toList());
    }

    public AccountDTO getAccountById(Long id) {
        Account account = repository.findById(id).get();
        return AccountMapper.accountToDto(account);
    }

    public AccountDTO createAccount(AccountDTO dto) {
        // Seteamos por default que siempre va a tener una caja de ahorra cuando se crea una cuenta
        // TODO: esto habria que refactorizarlo, no me gusto, ya que si quiero crear otro tipo de cuenta,
        //  por default va a ser de tipo caja de ahorro, y eso esta mal...
        //dto.setAccountType(AccountType.SAVINGS_BANK);

        // Seteamos el saldo en 0 por default
        dto.setAmount(BigDecimal.ZERO);
        //dto.setAmount(new BigDecimal(0)); //Otra forma de hacerlo que encontre, a traves de su constructor

        Account accountSaved = repository.save(AccountMapper.dtoToAccount(dto));
        return AccountMapper.accountToDto(accountSaved);
    }

    public String deleteAccount(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return "La cuenta con id " + id + " ha sido eliminada!";
        } else {
            throw new EntityNotExistsException("La cuenta a eliminar elegida no existe!");
        }
    }

    public AccountDTO updateAllAccount(Long id, AccountDTO dto) {
        // Primero verifico si existe un usuario con ese id en la BD
        // Y tambien valida que todos los datos del "dto" no vienen en null
        if(repository.existsById(id) && validateAccountDtoAttributes(dto)) {

            // Consigo el usuario a modificar desde la BD
            Account accountToModify = repository.findById(id).get();

            // LOGICA DEL PUT
            accountToModify.setAccountType(dto.getAccountType());
            accountToModify.setCbu(dto.getCbu());
            accountToModify.setAlias(dto.getAlias());
            accountToModify.setAmount(dto.getAmount());
            //accountToModify.setOwner(dto.getOwner());

            // Persistimos la modificacion del account en la BD
            Account accountModified = repository.save(accountToModify);

            return AccountMapper.accountToDto(accountModified);
        }

        if(!repository.existsById(id) && !validateAccountDtoAttributes(dto))
            throw new FatalErrorException("La cuenta con id " + id +
                    " no existe! Y ademas uno o varios atributos son nulos");

        if(!repository.existsById(id))
            throw new EntityNotExistsException("La cuenta con id " + id + " no existe!");

        if(!validateAccountDtoAttributes(dto))
            throw new EntityAttributesNullException("Uno o varios de los atributos enviados son nulos");

        return null;
    }

    public boolean validateAccountDtoAttributes(AccountDTO dto) {
        return dto.getAccountType() != null &&
                dto.getCbu() != null &&
                dto.getAlias() != null &&
                dto.getAmount() != null; //&&
                //dto.getOwner() != null;
    }

    public AccountDTO updateAccount(Long id, AccountDTO dto) {
        // Primero verifico si existe un usuario con ese id en la BD
        if(repository.existsById(id)) {

            // Consigo el usuario a modificar desde la BD
            Account accountToModify = repository.findById(id).get();

            // Validar que datos no vienen en null para modificar el objeto traido de la BD
            // LOGICA DEL PATCH|
            if(dto.getAccountType() != null)
                accountToModify.setAccountType(dto.getAccountType());

            if(dto.getCbu() != null)
                accountToModify.setCbu(dto.getCbu());

            if(dto.getAlias() != null)
                accountToModify.setAlias(dto.getAlias());

            if(dto.getAmount() != null)
                accountToModify.setAmount(dto.getAmount());

//            if(dto.getOwner() != null)
//                accountToModify.setOwner(dto.getOwner());

            // Persistimos la modificacion del usuario en la BD
            Account accountModified = repository.save(accountToModify);

            return AccountMapper.accountToDto(accountModified);
        }
        throw new EntityNotExistsException("La cuenta con id " + id + " no existe!");
    }
}
