package com.bluebox.productstore.persistence.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_company")
@Setter
@NoArgsConstructor
public class CompanyEntity {
    private Long id;
    private String name;
    private List<ProductEntity> products;

    public CompanyEntity(String name) {
        this.name = name;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    public List<ProductEntity> getProducts() {
        return products;
    }
}
