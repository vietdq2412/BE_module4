package com.codegym.service.accountService;

import com.codegym.model.Account;
import com.codegym.repository.IAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {
    @Autowired
    IAccountRepo accountRepo;

    @Override
    public List<Account> findAll() {
        return accountRepo.findAll();
    }

    @Override
    public boolean save(Account account) {
        boolean check = accountRepo.existsAccountByUsername(account.getUsername());
        if (!check) {
            accountRepo.save(account);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (accountRepo.existsById(id)) {
            accountRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Account findById(Long id) {
        return accountRepo.findById(id).get();
    }

    public Account findByUserName(String username) {
        return accountRepo.findAccountByUsername(username);
    }

    public boolean checkLogin(Account account) {
        if (accountRepo.findAccountByUsernameAndPassword(account.getUsername(), account.getPassword()) != null) {
            return true;
        }
        return false;
    }
}
