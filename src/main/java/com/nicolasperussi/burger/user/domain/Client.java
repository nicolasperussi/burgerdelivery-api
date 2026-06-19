package com.nicolasperussi.burger.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "clients")
public class Client extends User {

    private String phone;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("client")
    private List<Address> addresses;


    public Client() {
    }

    public Client(String name, String email, String password, String phone, List<Address> addresses) {
        super(name, email, password);
        this.phone = phone;
        this.addresses = addresses;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
