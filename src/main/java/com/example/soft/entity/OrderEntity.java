package com.example.soft.entity;

import com.example.soft.entity.enumeracion.ProductType;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "orders", schema = "public")
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_need_installation")
    private Boolean isNeedInstallation;

    @Column(name = "product_type")
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "order_time", updatable = false)
    private LocalDate orderTime;

    @Column(name = "execution_time")
    private LocalDate executionTime;

    @Column(name = "install_time")
    private LocalDate installTime;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private UserEntity customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private UserEntity employee;

    @OneToMany(mappedBy = "order",
            cascade = {CascadeType.ALL})
    private List<ProductDescriptionEntity> descriptionList;
}
