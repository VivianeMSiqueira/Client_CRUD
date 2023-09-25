package com.viviane.clients.repositories;

import com.viviane.clients.models.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = " select c from Client c ")
    Page<Client> findAllClients(final Pageable pageable);
}
