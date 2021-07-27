package com.example.soft.entity;

import com.example.soft.entity.enumeracion.ProductType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private boolean isNeedInstallation;
    @Column(name = "product_type")
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    @Column(name = "amount")
    private int amount;
    @Column(name = "order_time", updatable = false)
    private LocalDate orderTime;
    @Column(name = "execution_time")
    private LocalDate executionTime;
    @Column(name = "install_time")
    private LocalDate installTime;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private UserEntity customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private UserEntity employee;

    @JsonManagedReference
    @OneToMany(mappedBy = "order",
            cascade = {CascadeType.ALL})
    private List<ProductDescriptionEntity> descriptionList;


}
