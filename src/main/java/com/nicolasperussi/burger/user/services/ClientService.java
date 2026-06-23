package com.nicolasperussi.burger.user.services;

import com.nicolasperussi.burger.core.exceptions.ResourceNotFoundException;
import com.nicolasperussi.burger.user.domain.Address;
import com.nicolasperussi.burger.user.domain.Client;
import com.nicolasperussi.burger.user.dto.CreateAddressRequest;
import com.nicolasperussi.burger.user.dto.CreateClientRequest;
import com.nicolasperussi.burger.user.repositories.AddressRepository;
import com.nicolasperussi.burger.user.repositories.ClientRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    public Client findById(String clientId) {
        return this.repository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException(
                "Couldn't find User with id " + clientId));
    }

    public Client create(@NonNull CreateClientRequest data) {
        Client newClient = new Client(data.name(), data.email(), data.password(), data.phone());

        return this.repository.save(newClient);
    }

    // add address to client (turn all other addresses to default = false)
    public Address addAddressToClient(@NonNull CreateAddressRequest data, String clientId) {

        // search for client
        Client client = repository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException(
                "Couldn't find Client with id " + clientId));

        // instantiate new address
        Address newAddress = new Address(data.street(), data.number(), data.cep(), data.isDefault(), client);
        client.addAddress(newAddress);

        // check if new address is default
        // if it is default, change all other client addresses isDefault to false
        if (newAddress.getDefault()) {
            List<Address> addresses = addressRepository.findByClientId(clientId);
            if (!addresses.isEmpty()) {
                for (Address address : addresses) {
                    address.setDefault(false);
                    addressRepository.save(address);
                }
            }
        }

        return addressRepository.save(newAddress);

    }

    public List<Address> findAddressesById(String clientId) {
        return this.findById(clientId).getAddresses();
    }
}
