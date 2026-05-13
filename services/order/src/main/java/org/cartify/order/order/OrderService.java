package org.cartify.order.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cartify.order.customer.CustomerClient;
import org.cartify.order.exception.BusinessException;
import org.cartify.order.orderLine.OrderLineService;
import org.cartify.order.orderLine.OrderLineRequest;
import org.cartify.order.product.ProductClient;
import org.cartify.order.product.PurchaseRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;

    public Integer createOrder(@Valid OrderRequest request) {
        // Check if the customer exists --> openFeign
        var customer = customerClient.findCustomerById(request.customerId()).orElseThrow(
                () -> new BusinessException("Cannot create order." +
                        "No customer exists with this id" + request.customerId() + "."
                ));

        productClient.purchaseProducts(request.products());
        var order = repository.save(mapper.toOrder(request));

        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()

                    )
            );
        }

        // TODO:: Start payment process

        // Send the order confirmation --> notification-MS (Kafka)
        return null;
    }
}
