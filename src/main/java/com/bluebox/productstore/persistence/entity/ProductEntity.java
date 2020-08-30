package com.bluebox.productstore.persistence.entity;

import lombok.AllArgsConstructor;
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
    private String seller;

    public ProductEntity(String name, double price, String company, String seller) {
        this.name = name;
        this.price = price;
        this.company = company;
        this.seller = seller;
    }

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

    @Column(name = "seller")
    public String getSeller() {
        return seller;
    }

    public void setNewValueForField(String field, String newValue) throws Exception {
        switch (field) {
            case "name" -> this.name = newValue;
            case "price" -> this.price = Double.parseDouble(newValue);
            case "company" -> this.company = newValue;
            default -> throw new Exception("wrong field");
        }
    }
}
