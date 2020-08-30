package com.bluebox.productstore.persistence.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Kamran Ghiasvand
 */
@Entity
@Table(name = "tbl_user")
@Setter
@NoArgsConstructor
public class UserEntity {
    private Long id;
    private String username;
    private String password;
    //type can be customer or seller or manager
    private String type;

    public UserEntity(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

}
