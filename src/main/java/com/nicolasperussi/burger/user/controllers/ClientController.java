package com.nicolasperussi.burger.user.controllers;

import com.nicolasperussi.burger.user.domain.Address;
import com.nicolasperussi.burger.user.domain.Client;
import com.nicolasperussi.burger.user.dto.CreateAddressRequest;
import com.nicolasperussi.burger.user.dto.CreateClientRequest;
import com.nicolasperussi.burger.user.services.ClientService;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@NonNull @PathVariable String id) {
        Client client = this.service.findById(id);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/{id}/addresses")
    public ResponseEntity<List<Address>> findAddressesByClientId(@NonNull @PathVariable String id) {
        List<Address> addressList = this.service.findAddressesById(id);

        if (addressList.isEmpty()) {return ResponseEntity.noContent().build();}

        return ResponseEntity.ok(addressList);
    }

    @PostMapping()
    public ResponseEntity<Client> create(@Valid @RequestBody CreateClientRequest data, UriComponentsBuilder uriBuilder) {
        Client client = this.service.create(data);
        URI uri = uriBuilder.path("/client/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/{id}/addresses")
    public ResponseEntity<Address> addAddressToClient(@Valid @RequestBody CreateAddressRequest data, @NonNull @PathVariable String id, UriComponentsBuilder uriBuilder) {
        Address newAddress = this.service.addAddressToClient(data, id);

        URI uri = uriBuilder.path("/addresses/{addressId}").buildAndExpand(newAddress.getId()).toUri();
        return ResponseEntity.created(uri).body(newAddress);
    }

}
