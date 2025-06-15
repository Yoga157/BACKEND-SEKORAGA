package com.skrg.sekoraga.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AdUserAuthorityId implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "authority_id")
    private Long authorityId;

    public AdUserAuthorityId() {
    }

    public AdUserAuthorityId(Long userId, Long authorityId) {
        this.userId = userId;
        this.authorityId = authorityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AdUserAuthorityId that = (AdUserAuthorityId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(authorityId, that.authorityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, authorityId);
    }
}
