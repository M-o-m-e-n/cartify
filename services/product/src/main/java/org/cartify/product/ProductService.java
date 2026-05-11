package org.cartify.product;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cartify.exception.ProductPurchaseException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Integer createProduct(@Valid ProductRequest request) {
        var product = mapper.toProduct(request);
        return repository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        if (request == null || request.isEmpty()) {
            return List.of();
        }

        var productIds = request.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        var products = repository.findAllById(productIds);
        var productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, product -> product));

        var responses = request.stream()
                .map(item -> {
                    var product = productMap.get(item.productId());
                    if (product == null) {
                        throw new EntityNotFoundException("Product not found with the id:" + item.productId());
                    }
                    if (product.getAvailableQuantity() < item.quantity()) {
                        throw new ProductPurchaseException("Insufficient stock for product id:" + item.productId());
                    }

                    product.setAvailableQuantity(product.getAvailableQuantity() - item.quantity());
                    return mapper.toProductPurchaseResponse(product, item.quantity());
                })
                .toList();

        repository.saveAll(productMap.values());
        return responses;
    }

    public ProductResponse getProduct(Integer productId) {
        return repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with the id:" + productId));
    }

    public List<ProductResponse> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .toList();
    }
}
