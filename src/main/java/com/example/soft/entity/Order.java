package com.example.soft.entity;
import com.example.soft.entity.enumeracion.ProductType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    @Column(name= "employee_id")
    private long employeeId;
    @Column(name="customer_id")
    private long customerId;
    @Column(name = "is_need_installation")
    private boolean isNeedInstallation;
    @Column (name = "product_type")
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    @Column (name = "amount")
    private int amount;
    @Column (name = "order_time")
    private Date orderTime;
    @Column (name = "execution_time")
    private Date executionTime;
    @Column(name = "install_time")
    private Date installTime;



    @JsonManagedReference
    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "customer_id", nullable = false,insertable = false, updatable = false)
    private User user;

    @OneToMany(mappedBy = "order"
    ,cascade = {CascadeType.DETACH,CascadeType.MERGE
            ,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.REMOVE})
    private List <ProductDescription> descriptionList;

}
