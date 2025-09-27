package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.AppUser;
import com.example.demo.models.Client;


public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUser(AppUser user);


}
