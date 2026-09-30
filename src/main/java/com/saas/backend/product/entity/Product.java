package com.saas.backend.product.entity;

import com.saas.backend.category.entity.Category;
import com.saas.backend.tenant.entity.Tenant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "products",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_tenant_sku",
                        columnNames = {"tenant_id", "sku"}
                )
        }
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private String name;

    private String slug;

    private String sku;

    @Column(columnDefinition = "TEXT")
    private String description;

    private BigDecimal mrp;

    private BigDecimal sellingPrice;

    private BigDecimal factory_cost;

    private String brand;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;
}
