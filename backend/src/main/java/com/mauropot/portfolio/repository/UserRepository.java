package com.mauropot.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mauropot.portfolio.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // Per cercare l’utente dal suo username (obbligatorio per login)
    Optional<User> findByUsername(String username);

    // Opzionale: per verificare se uno username esiste già
    boolean existsByUsername(String username);
}
