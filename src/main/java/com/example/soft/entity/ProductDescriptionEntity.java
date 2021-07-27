package com.example.soft.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "product_description", schema = "public")
@NoArgsConstructor
public class ProductDescriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "width")
    private int width;
    @Column(name = "height")
    private int height;
    @Column(name = "color")
    private String color;
    @Column(name = "comments")
    private String comments;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

}
