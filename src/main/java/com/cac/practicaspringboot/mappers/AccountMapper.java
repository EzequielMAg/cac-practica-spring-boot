package com.cac.practicaspringboot.mappers;

import com.cac.practicaspringboot.models.Account;
import com.cac.practicaspringboot.models.dtos.AccountDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountMapper {

    // TODO: REFACTORIZAR IMPLEMENTANDO PATRON DE DISEÑO BUILDER
    // Este patron de diseño creacional nos servira para crear un objeto solamente con los atributos que necesito
    // y a los demas atributos los dejaria con null o vacio. A los demas atributos los asignaria despues con el setter,
    // cuando sea necesario.
    public static Account dtoToAccount(AccountDTO dto) {
        Account account = new Account();
        account.setAccountType(dto.getAccountType());
        account.setCbu(dto.getCbu());
        account.setAlias(dto.getAlias());
        account.setAmount(dto.getAmount());
        return account;
    }

    public static AccountDTO accountToDto(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setId(account.getId());
        dto.setAccountType(account.getAccountType());
        dto.setCbu(account.getCbu());
        dto.setAlias(account.getAlias());
        dto.setAmount(account.getAmount());
        return dto;
    }
}
