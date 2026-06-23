package com.nicolasperussi.burger.user.repositories;

import com.nicolasperussi.burger.user.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
    List<Address> findByClientId(String clientId);
}
