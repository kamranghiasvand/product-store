package com.bluebox.productstore.persistence.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart")
@Setter
@NoArgsConstructor
public class CartEntity {
    private Long id;
    private UserEntity user;



    private List<ProductEntity> products;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    public List<ProductEntity> getProducts() {
        return products;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public UserEntity getUser() {
        return user;
    }

}
