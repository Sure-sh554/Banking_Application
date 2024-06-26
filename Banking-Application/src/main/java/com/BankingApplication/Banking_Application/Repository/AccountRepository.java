package com.BankingApplication.Banking_Application.Repository;

import com.BankingApplication.Banking_Application.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

}
