package org.cartify.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        int id,
        String name,
        @NotNull(message = "Product description is required.")
        String description,
        @Positive(message = "Available quantity should be positive.")
        int availableQuantity,
        @Positive(message = "Price quantity should be positive.")
        BigDecimal price,
        @NotNull(message = "Category id is required.")
        Integer categoryId

) {
}
