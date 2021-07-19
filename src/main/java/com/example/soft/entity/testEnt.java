package com.example.soft.entity;

import javax.persistence.*;

@Entity
@Table(name = "test_table")

public class testEnt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "name")
    String name1;
}
