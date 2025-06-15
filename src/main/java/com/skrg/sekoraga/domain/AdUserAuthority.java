package com.skrg.sekoraga.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "ad_user_authority")
public class AdUserAuthority implements Serializable {
    @EmbeddedId
    private AdUserAuthorityId id;

    @Column(name = "uuid")
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private AdUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("authorityId")
    @JoinColumn(name = "authority_id")
    private AdAuthority authority;

    // getters and setters
    public AdUserAuthorityId getId() {
        return id;
    }

    public void setId(AdUserAuthorityId id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public AdUser getUser() {
        return user;
    }

    public void setUser(AdUser user) {
        this.user = user;
    }

    public AdAuthority getAuthority() {
        return authority;
    }

    public void setAuthority(AdAuthority authority) {
        this.authority = authority;
    }
}
