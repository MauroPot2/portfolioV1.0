package com.mauropot.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mauropot.portfolio.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Cerca per username (login tradizionale)
    Optional<User> findByUsername(String username);
    
    // Cerca per email (login via email)
    Optional<User> findByEmail(String email);
    
    // Verifica esistenza username
    boolean existsByUsername(String username);
    
    // Verifica esistenza email
    boolean existsByEmail(String email);
    
    // Query personalizzata per verificare username o email
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = :username OR u.email = :email")
    boolean existsByUsernameOrEmail(String username, String email);
    
    // Query per ottenere l'utente con i progetti (eager loading)
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.projects WHERE u.id = :userId")
    Optional<User> findByIdWithProjects(Long userId);
}