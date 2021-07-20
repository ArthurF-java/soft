package com.example.soft.entity;

import javax.persistence.*;

@Entity
@Table(name = "test_table")

public class TestEnt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    long id;
    @Column(name = "name")
    String name;
}
