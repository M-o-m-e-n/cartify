package org.cartify.product;

import java.math.BigDecimal;

public record ProductResponse(
        int id,
        String name,
        String description,
        int availableQuantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription
) {
}
