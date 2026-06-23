package com.nicolasperussi.burger.user.controllers;

import com.nicolasperussi.burger.user.domain.Address;
import com.nicolasperussi.burger.user.services.AddressService;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

    @Autowired
    private AddressService service;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NonNull @PathVariable String id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/default")
    public ResponseEntity<Address> setDefault(@NonNull @PathVariable String id) {
        Address address = this.service.setDefault(id);
        return ResponseEntity.ok(address);
    }
}
