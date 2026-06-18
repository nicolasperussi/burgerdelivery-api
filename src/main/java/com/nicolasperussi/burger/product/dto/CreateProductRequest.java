package com.nicolasperussi.burger.product.dto;

import com.nicolasperussi.burger.product.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateProductRequest(
        @NotBlank(message = "The product must have a name.") String name,
        @Size(min = 10, message = "The description must have at least 10 characters.") String description,
        @Positive(message = "The price must be bigger than zero.") int price,
        @NotNull(message = "The product must have a category.") ProductCategory category
) {}
