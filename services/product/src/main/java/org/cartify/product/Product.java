package org.cartify.product;

import jakarta.persistence.*;
import lombok.*;
import org.cartify.category.Category;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
public class Product {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    private int availableQuantity;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
