package com.nicolasperussi.burger.user.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private String street;
    private String number;
    private String cep;
    private Boolean isDefault;

    public Client getClient() {return client;}

    public void setClient(Client client) {this.client = client;}
}
