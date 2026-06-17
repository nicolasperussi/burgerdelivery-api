package com.nicolasperussi.burger.product;

import com.nicolasperussi.burger.product.dto.CreateProductRequest;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    // find all products (add filtering by category)
    public Page<Product> findAll(Pageable pageable) {return repository.findAll(pageable);}

    // find a single product by id
    // create a product
    public Product create(@NonNull CreateProductRequest data) {
        Product newProduct = new Product(data.name(), data.description(), data.price(), data.category(), true);

        return this.repository.save(newProduct);
    }
    // soft delete a product

}
