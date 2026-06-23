package com.nicolasperussi.burger.user.dto;

public record CreateAddressRequest(String street, String number, String cep, Boolean isDefault) {
}
