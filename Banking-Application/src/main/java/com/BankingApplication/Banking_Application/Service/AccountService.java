package com.BankingApplication.Banking_Application.Service;

import com.BankingApplication.Banking_Application.Dto.AccountDto;

import java.util.List;


public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);
    AccountDto deposit (Long id,double amount);
    AccountDto withdraw (Long id, double amount);
    List<AccountDto> getAllAccounts();
    void deleteAccount(Long id);
}
