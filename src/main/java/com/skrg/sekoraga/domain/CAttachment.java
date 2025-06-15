package com.skrg.sekoraga.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "c_attachment")
public class CAttachment extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachment_id")
    private Long attachmentId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "mime_type")
    private String mimeType;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "type", nullable = false)
    private String type;

}
