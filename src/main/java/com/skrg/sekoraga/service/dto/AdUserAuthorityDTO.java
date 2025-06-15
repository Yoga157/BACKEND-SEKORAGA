package com.skrg.sekoraga.service.dto;

import java.io.Serializable;
import java.util.UUID;

public class AdUserAuthorityDTO implements Serializable {
    private Long userId;
    private Long authorityId;
    private UUID uuid;

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

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
