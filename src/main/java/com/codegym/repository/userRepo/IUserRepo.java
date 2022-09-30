package com.codegym.repository.userRepo;

import com.codegym.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<Account, Long> {
}
