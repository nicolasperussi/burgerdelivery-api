package com.nicolasperussi.burger.user.services;

import com.nicolasperussi.burger.core.exceptions.ResourceNotFoundException;
import com.nicolasperussi.burger.user.domain.Address;
import com.nicolasperussi.burger.user.domain.Client;
import com.nicolasperussi.burger.user.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    public Address findById(String addressId) {
        return this.repository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException(
                "Couldn't find Address with id " + addressId));
    }

    // update service

    // set default
    public Address setDefault(String addressId) {
        Address address = this.findById(addressId);
        Client client = address.getClient();

        List<Address> addresses = repository.findByClientId(client.getId());
        if (!addresses.isEmpty()) {
            for (Address a : addresses) {
                a.setDefault(false);
                repository.save(address);
            }
        }

        address.setDefault(true);
        this.repository.save(address);
        return address;
    }

    // delete service
    public void delete(String addressId) {
        Address address = this.findById(addressId);

        this.repository.delete(address);
    }
}
