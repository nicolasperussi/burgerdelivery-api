package com.nicolasperussi.burger.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
