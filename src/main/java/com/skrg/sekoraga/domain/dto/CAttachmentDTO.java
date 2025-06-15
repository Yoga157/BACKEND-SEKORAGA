package com.skrg.sekoraga.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CAttachmentDTO {
    private String id;
    private String fileName;
    private String mimeType;
    private String documentType;
    private String type;
    // Optionally, add a field for download URL or file data if needed
}
