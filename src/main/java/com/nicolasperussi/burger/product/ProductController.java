package com.nicolasperussi.burger.product;

import com.nicolasperussi.burger.product.dto.CreateProductRequest;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping()
    public ResponseEntity<Page<Product>> findAll(@PageableDefault(size = 10, page = 0, sort = "category") Pageable pageable) {
        Page<Product> products = this.service.findAll(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@NonNull @PathVariable String id) {
        Product product = this.service.findById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping()
    public ResponseEntity<Void> create(@Valid @RequestBody CreateProductRequest data, UriComponentsBuilder uriBuilder) {
        Product product = this.service.create(data);
        URI uri = uriBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@NonNull @PathVariable String id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
