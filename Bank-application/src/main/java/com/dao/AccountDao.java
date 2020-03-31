package com.dao;

import com.model.Account;
import com.model.Branch;
import com.example.exceptions.LowBalanceException;
import com.example.model.Account;
import com.example.model.Branch;
import com.example.model.Customer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDao {
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public List<Account> listAllAccounts() {
        Criteria criteria = getSession().createCriteria(Account.class);
        return criteria.list();
    }

    public void saveOrUpdate(Double balance, int customer_id, int branch_id) {
        Account account = new Account();
        account.setBalance(balance);
        Customer customer = (Customer) getSession().load(Customer.class, customer_id);
        Branch branch = (Branch) getSession().load(Branch.class, branch_id);
        account.setBranch(branch);
        account.setCustomer(customer);
        getSession().save(account);
    }

    public Account findAccountById(int id) {
        return (Account) getSession().get(Account.class, id);
    }

    public void deleteAccount(int id) {
        Account account = (Account) getSession().get(Account.class, id);
        getSession().delete(account);
    }

    public void withdraw(Account account, Double amount){
        Double accountBalance = account.getBalance();
        double finalBalance = accountBalance - amount;
        if(finalBalance<0){
            throw new LowBalanceException("not having sufficient balance");
        }else{
        account.setBalance(finalBalance);
        getSession().saveOrUpdate(account);
        }

    }
    public void deposit(Account account, Double amount){
        Double accountBalance = account.getBalance();
        double finalBalance = accountBalance + amount;
        account.setBalance(finalBalance);
        getSession().saveOrUpdate(account);
    }

}import com.example.exceptions.LowBalanceException;
import com.example.model.Account;
import com.example.model.Branch;
import com.example.model.Customer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDao {
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public List<Account> listAllAccounts() {
        Criteria criteria = getSession().createCriteria(Account.class);
        return criteria.list();
    }

    public void saveOrUpdate(Double balance, int customer_id, int branch_id) {
        Account account = new Account();
        account.setBalance(balance);
        Customer customer = (Customer) getSession().load(Customer.class, customer_id);
        Branch branch = (Branch) getSession().load(Branch.class, branch_id);
        account.setBranch(branch);
        account.setCustomer(customer);
        getSession().save(account);
    }

    public Account findAccountById(int id) {
        return (Account) getSession().get(Account.class, id);
    }

    public void deleteAccount(int id) {
        Account account = (Account) getSession().get(Account.class, id);
        getSession().delete(account);
    }

    public void withdraw(Account account, Double amount){
        Double accountBalance = account.getBalance();
        double finalBalance = accountBalance - amount;
        if(finalBalance<0){
            throw new LowBalanceException("Not having sufficient balance");
        }else{
        account.setBalance(finalBalance);
        getSession().saveOrUpdate(account);
        }

    }
    public void deposit(Account account, Double amount){
        Double accountBalance = account.getBalance();
        double finalBalance = accountBalance + amount;
        account.setBalance(finalBalance);
        getSession().saveOrUpdate(account);
    }

}
