package org.cartify.order.orderLine;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderLineRequest(
        Integer id,
        Integer orderId,
        @NotNull
        Integer productId,
        @Positive
        Integer quantity) {
}
