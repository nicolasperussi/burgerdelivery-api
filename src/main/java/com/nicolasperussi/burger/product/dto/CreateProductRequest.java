package com.nicolasperussi.burger.product.dto;

import com.nicolasperussi.burger.product.ProductCategory;

public record CreateProductRequest(String name, String description, int price, ProductCategory category) {}
