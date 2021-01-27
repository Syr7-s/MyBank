package org.kodluyoruz.mybank.account.demanddepositaccount.repository;

import org.kodluyoruz.mybank.account.demanddepositaccount.entity.DemandDepositAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DemandDepositAccountRepository extends CrudRepository<DemandDepositAccount, Integer> {
    DemandDepositAccount findByCustomer_CustomerID(long customerID);
}
