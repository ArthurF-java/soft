package com.example.soft.entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders", schema = "public")
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "is_need_installation")
    private boolean isNeedInstallation;
    @Column (name = "product_type")
    private String productType;
    @Column (name = "amount")
    private int amount;
    @Column (name = "order_time")
    private Date order_time;
    @Column (name = "execution_time")
    private Date execution_time;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE
            ,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.REMOVE})
    @JoinColumn(name = "customer_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order"
    ,cascade = {CascadeType.DETACH,CascadeType.MERGE
            ,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.REMOVE})
    private List <ProductDescription> descriptionList;

}
