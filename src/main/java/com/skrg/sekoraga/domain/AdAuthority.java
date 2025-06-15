package com.skrg.sekoraga.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
@Table(name = "ad_authority")
public class AdAuthority {

    @Id
    @Column(name = "authority_id")
    private Long authorityId = (long) (Math.random() * 90_000L) + 10_000L;

    @Column(name = "uuid", nullable = false, unique = true)
    private UUID uuid = UUID.randomUUID();

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
