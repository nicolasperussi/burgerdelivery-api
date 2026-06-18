package com.nicolasperussi.burger.product;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    @Test
    void shouldChangeProductStatusToInactiveWhenDeletingExistingProduct() {
        String fakeId = "fake-uuid";

        Product fakeProduct = new Product("Hamburguer", "Delicioso", 2500, ProductCategory.SANDWICH, true);
        fakeProduct.setId(fakeId);

        Mockito.when(repository.findById(fakeId)).thenReturn(Optional.of(fakeProduct));

        Mockito.when(repository.save(Mockito.any(Product.class))).thenReturn(fakeProduct);

        Product result = service.delete(fakeId);

        Assertions.assertNotNull(result);

        Assertions.assertFalse(result.getAvailable());

        Mockito.verify(repository).save(fakeProduct);
    }
}