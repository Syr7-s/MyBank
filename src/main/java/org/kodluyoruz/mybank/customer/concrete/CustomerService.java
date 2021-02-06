package org.kodluyoruz.mybank.customer.concrete;

import org.kodluyoruz.mybank.account.demanddepositaccount.concrete.DemandDepositAccount;
import org.kodluyoruz.mybank.account.savingsaccount.concrete.SavingsAccount;
import org.kodluyoruz.mybank.card.creditcard.concrete.CreditCard;
import org.kodluyoruz.mybank.customer.abstrct.CustomerRepository;
import org.kodluyoruz.mybank.customer.abstrct.ICustomService;
import org.kodluyoruz.mybank.customer.exception.CustomerCouldNotDeletedException;
import org.kodluyoruz.mybank.customer.exception.CustomerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomService<Customer> {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(long id) {
        Customer customer = customerRepository.findCustomerByCustomerID(id);
        if (customer != null) {
            return customer;
        } else {
            throw new CustomerNotFoundException("Customer Not Found.");
        }
    }

    @Override
    public Customer update(Customer customer) {
        Customer updatedCustomer = customerRepository.findCustomerByCustomerID(customer.getCustomerID());

        if (updatedCustomer != null) {
            updatedCustomer.setCustomerTC(customer.getCustomerTC());
            updatedCustomer.setCustomerName(customer.getCustomerName());
            updatedCustomer.setCustomerLastname(customer.getCustomerLastname());
            updatedCustomer.setCustomerPhone(customer.getCustomerPhone());
            updatedCustomer.setCustomerEmail(customer.getCustomerEmail());
            updatedCustomer.setCustomerAddress(customer.getCustomerAddress());
            updatedCustomer.setCustomerRemovable(customer.isCustomerRemovable());
            updatedCustomer.setCustomerBirthDate(customer.getCustomerBirthDate());

            return customerRepository.save(updatedCustomer);
        } else {
            throw new CustomerNotFoundException("Customer Not Found.");
        }
    }

    @Override
    public void delete(long customerId) {
        Customer deletedCustomer = customerRepository.findCustomerByCustomerID(customerId);
        List<Integer> debts = deletedCustomer.getCreditCards().stream().map(CreditCard::getCardDebt).collect(Collectors.toList());
        boolean isCreditCardDelete = isZero(debts);
        List<Integer> demandAccountBalance = deletedCustomer.getDemandDepositAccounts().stream().map(DemandDepositAccount::getDemandDepositAccountBalance).collect(Collectors.toList());
        boolean isDemandAccountDelete = isZero(demandAccountBalance);
        List<Integer> savingsAccountBalance = deletedCustomer.getSavingsAccounts().stream().map(SavingsAccount::getSavingsAccountBalance).collect(Collectors.toList());
        boolean isSavingsAccountDelete = isZero(savingsAccountBalance);
        if (isCreditCardDelete && isDemandAccountDelete && isSavingsAccountDelete) {
            customerRepository.delete(deletedCustomer);
        } else {
            throw new CustomerCouldNotDeletedException("Customer could not deleted .");
        }
    }
    private boolean isZero(List<Integer> debts) {
        int counter = 0;
        for (Integer debt : debts) {
            if (debt == 0) {
                counter++;
            }
        }
        return counter == debts.size();
    }
/*
    public Customer getCustomerByID(long id) {
        Customer customer = customerRepository.findCustomerByCustomerID(id);
        if (customer != null) {
            return customer;
        } else {
            throw new CustomerNotFoundException("Customer Not Found.");
        }
    }

    public Customer updateCustomer(Customer customer) {
        Customer updatedCustomer = customerRepository.findCustomerByCustomerID(customer.getCustomerID());

        if (updatedCustomer != null) {
            updatedCustomer.setCustomerTC(customer.getCustomerTC());
            updatedCustomer.setCustomerName(customer.getCustomerName());
            updatedCustomer.setCustomerLastname(customer.getCustomerLastname());
            updatedCustomer.setCustomerPhone(customer.getCustomerPhone());
            updatedCustomer.setCustomerEmail(customer.getCustomerEmail());
            updatedCustomer.setCustomerAddress(customer.getCustomerAddress());
            updatedCustomer.setCustomerRemovable(customer.isCustomerRemovable());
            updatedCustomer.setCustomerBirthDate(customer.getCustomerBirthDate());

            return customerRepository.save(updatedCustomer);
        } else {
            throw new CustomerNotFoundException("Customer Not Found.");
        }
    }

    public void deleteCustomer(long id) {
        Customer deletedCustomer = customerRepository.findCustomerByCustomerID(id);
        List<Integer> debts = deletedCustomer.getCreditCards().stream().map(CreditCard::getCardDebt).collect(Collectors.toList());
        boolean isCreditCardDelete = isZero(debts);
        List<Integer> demandAccountBalance = deletedCustomer.getDemandDepositAccounts().stream().map(DemandDepositAccount::getDemandDepositAccountBalance).collect(Collectors.toList());
        boolean isDemandAccountDelete = isZero(demandAccountBalance);
        List<Integer> savingsAccountBalance = deletedCustomer.getSavingsAccounts().stream().map(SavingsAccount::getSavingsAccountBalance).collect(Collectors.toList());
        boolean isSavingsAccountDelete = isZero(savingsAccountBalance);
        if (isCreditCardDelete && isDemandAccountDelete && isSavingsAccountDelete) {
            customerRepository.delete(deletedCustomer);
        } else {
            throw new CustomerCouldNotDeletedException("Customer could not deleted .");
        }
    }

    private boolean isZero(List<Integer> debts) {
        int counter = 0;
        for (Integer debt : debts) {
            if (debt == 0) {
                counter++;
            }
        }
        return counter == debts.size();
    }
    */

}