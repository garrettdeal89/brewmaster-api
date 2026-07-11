package com.gdeal.brewmaster.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gdeal.brewmaster.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    
    Optional<UserAccount> findByUsername(String username);
    Optional<UserAccount> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
