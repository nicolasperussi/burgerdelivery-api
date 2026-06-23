package com.nicolasperussi.burger.user.repositories;

import com.nicolasperussi.burger.user.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
}
