package com.bluebox.productstore.persistence.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_token")
@Setter
@NoArgsConstructor
public class TokenEntity {
    private String context;
    private Date expireTime;
    private UserEntity user;
    private Long id;

    public TokenEntity(UserEntity user) {
        this.user = user;
        newTime();
    }

    private void newTime() {
        this.expireTime = new Date(System.currentTimeMillis() + 3600 * 1000);
    }

    public boolean tokenExpired() {
        return new Date(System.currentTimeMillis()).after(this.expireTime);
    }

    @Column(name = "context")
    public String getContext() {
        return context;
    }

    @Column(name = "expireTime")
    public Date getExpireTime() {
        return expireTime;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public UserEntity getUser() {
        return user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
}
