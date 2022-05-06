package com.example.bankbackendapi.repository;

import com.example.bankbackendapi.model.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findById(Long id);
    Optional<ClientEntity> findByPassport(String passport);
    ClientEntity getByPassport(String passport);
}
