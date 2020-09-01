package com.bluebox.productstore.persistence.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tbl_token")
@Setter
@NoArgsConstructor
public class TokenEntity {
    private String context;
    private String username;
    private Date expireTime;

    public TokenEntity(String username) {
        this.username = username;
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

    @Id
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    @Column(name = "expireTime")
    public Date getExpireTime() {
        return expireTime;
    }


}
