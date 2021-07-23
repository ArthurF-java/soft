package com.example.soft.entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "product_description",schema = "public")
@NoArgsConstructor
public class ProductDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "width")
    private int width;
    @Column(name = "height")
    private int height;
    @Column(name = "color")
    private String color;
    @Column(name = "comments")
    private String comments;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE
            ,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.REMOVE})
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
