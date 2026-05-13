package org.cartify.order.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull
        Integer productId,
        @Positive
        Integer quantity
) {
}
