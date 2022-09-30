package com.codegym.repository;

import com.codegym.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;

public interface IAccountRepo extends JpaRepository<Account, Long> {
    Account findAccountByUsernameAndPassword(String userName, String password);
    Account findAccountByUsername(String username);
    boolean existsAccountByUsername(String username);
}
