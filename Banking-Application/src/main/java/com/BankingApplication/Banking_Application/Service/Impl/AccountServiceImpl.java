package com.BankingApplication.Banking_Application.Service.Impl;

import com.BankingApplication.Banking_Application.Dto.AccountDto;
import com.BankingApplication.Banking_Application.Entity.Account;
import com.BankingApplication.Banking_Application.Mapper.AccountMapper;
import com.BankingApplication.Banking_Application.Repository.AccountRepository;
import com.BankingApplication.Banking_Application.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account findAccount = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account Does not exist"));
        return AccountMapper.mapToAccountDto(findAccount);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account deposit = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account Does not exist"));
        double total = deposit.getBalance() + amount;
        deposit.setBalance(total);
        Account updatedAccount = accountRepository.save(deposit);

        return AccountMapper.mapToAccountDto(updatedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account Does not exist"));
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient Amount");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(account -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account Does not exist"));
        accountRepository.deleteById(id);

    }


}
