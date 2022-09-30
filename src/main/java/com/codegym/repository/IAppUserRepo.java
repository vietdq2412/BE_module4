package com.codegym.repository;

import com.codegym.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppUserRepo extends JpaRepository<AppUser, Long> {
}
