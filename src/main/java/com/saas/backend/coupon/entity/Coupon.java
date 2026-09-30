package com.saas.backend.coupon.entity;

import com.saas.backend.tenant.entity.Tenant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "coupons",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_tenant_coupon_code",
                        columnNames = {"tenant_id", "code"}
                )
        }
)
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    private String code;

    private String description;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    private BigDecimal discountValue;

    private BigDecimal minimumOrderAmount;

    private BigDecimal maximumDiscount;

    private Integer usageLimit;

    private Integer usedCount;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private CouponStatus active;
}
