package com.nicolasperussi.burger.user.services;

import com.nicolasperussi.burger.core.exceptions.ResourceNotFoundException;
import com.nicolasperussi.burger.user.domain.Address;
import com.nicolasperussi.burger.user.domain.Client;
import com.nicolasperussi.burger.user.dto.CreateAddressRequest;
import com.nicolasperussi.burger.user.repositories.AddressRepository;
import com.nicolasperussi.burger.user.repositories.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    void shouldTurnPreviousAddressToFalseWhenNewAddressIsDefault() {
        String clientId = "fake-client-id";

        Client fakeClient = new Client("Nicolas", "nicolas@email.com", "senha", "99999999");
        fakeClient.setId(clientId);

        Address oldAddress = new Address("Rua Velha", "10", "000", true, fakeClient);
        oldAddress.setId("old-address-id");

        CreateAddressRequest request = new CreateAddressRequest("Rua Nova", "20", "123", true);

        Mockito.when(clientRepository.findById(clientId)).thenReturn(Optional.of(fakeClient));
        Mockito.when(addressRepository.findByClientId(clientId)).thenReturn(List.of(oldAddress));

        Mockito.when(addressRepository.save(Mockito.any(Address.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Address result = clientService.addAddressToClient(request, clientId);

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.getDefault());

        Assertions.assertFalse(oldAddress.getDefault());

        Mockito.verify(addressRepository).save(oldAddress);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenClientDoesNotExist() {
        String invalidClientId = "invalid-id";
        CreateAddressRequest request = new CreateAddressRequest("Rua Nova", "20", "123", true);

        Mockito.when(clientRepository.findById(invalidClientId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            clientService.addAddressToClient(request, invalidClientId);
        });

        Mockito.verify(addressRepository, Mockito.never()).findByClientId(Mockito.anyString());
        Mockito.verify(addressRepository, Mockito.never()).save(Mockito.any(Address.class));
    }
}