package com.bluebox.productstore.persistence.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tbl_product")
@Setter
@NoArgsConstructor
public class ProductEntity {

    private Long id;
    private String name;
    private double price;
    private String company;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    @Column(name = "company")
    public String getCompany() {
        return company;
    }
}
