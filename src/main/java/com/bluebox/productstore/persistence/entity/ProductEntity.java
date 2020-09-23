package com.bluebox.productstore.persistence.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
@Setter
@NoArgsConstructor
public class ProductEntity {

    private Long id;
    private String name;
    private double price;
    private UserEntity seller;
    private CompanyEntity company;



    private List<ProductEntity> carts;

    public ProductEntity(String name, double price, UserEntity seller, CompanyEntity company) {
        this.name = name;
        this.price = price;
        this.seller = seller;
        this.company = company;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    public CompanyEntity getCompany() {
        return company;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    public UserEntity getSeller() {
        return seller;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_id")
    )
    public List<ProductEntity> getCarts() {
        return carts;
    }
}
