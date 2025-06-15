package com.skrg.sekoraga.domain.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdUserDTO {

    private String id;

    private String username;

    private String email;

    private boolean status;

    private Set<AdAuthorityDTO> authorities;

    private String name;
    private Long age;
    private String educationalLevel;
    private CAttachmentDTO attachment;
    private String password;

    public AdUserDTO() {

    }
}
