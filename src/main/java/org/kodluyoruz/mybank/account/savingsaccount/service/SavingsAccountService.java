package org.kodluyoruz.mybank.account.savingsaccount.service;

import org.kodluyoruz.mybank.account.savingsaccount.entity.SavingsAccount;
import org.kodluyoruz.mybank.account.savingsaccount.repository.SavingsAccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SavingsAccountService {
    private final SavingsAccountRepository savingsAccountRepository;

    public SavingsAccountService(SavingsAccountRepository savingsAccountRepository) {
        this.savingsAccountRepository = savingsAccountRepository;
    }
    public SavingsAccount create(SavingsAccount savingsAccount){
        return savingsAccountRepository.save(savingsAccount);
    }
    public Optional<SavingsAccount> get(long accountIBAN){
        return savingsAccountRepository.findById(accountIBAN);
    }
    public Page<SavingsAccount> savingsAccounts(Pageable pageable){
        return savingsAccountRepository.findAll(pageable);
    }
    public SavingsAccount updateBalance(SavingsAccount savingsAccount){
        return savingsAccountRepository.save(savingsAccount);
    }
}
