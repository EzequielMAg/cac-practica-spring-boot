package com.cac.practicaspringboot.repositories;

import com.cac.practicaspringboot.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>  {


}
