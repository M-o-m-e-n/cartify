package org.cartify.order.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.cartify.order.product.PurchaseRequest;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "order amount should be positive.")
        BigDecimal amount,
        @NotNull(message = "Order method should be precised.")
        PaymentMethod paymentMethod,
        @NotNull
        @NotEmpty
        @NotBlank
        String customerId,
        @NotEmpty
        List<PurchaseRequest> products
) {
}
