package org.cartify.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record ProductPurchaseRequest(
        @NotNull(message = "Product is required.")
        Integer productId,
        @NotNull(message = "Quantity is required.")
        @Positive(message = "Quantity should be positive.")
        Integer quantity

) {
}
