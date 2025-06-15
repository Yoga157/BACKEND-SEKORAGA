package com.skrg.sekoraga.web.rest;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.skrg.sekoraga.domain.criteria.CAttachmentCriteria;
import com.skrg.sekoraga.domain.dto.CAttachmentDTO;
import com.skrg.sekoraga.service.CAttachmentQueryService;
import com.skrg.sekoraga.service.CAttachmentService;
import com.skrg.sekoraga.util.HeaderUtil;
import com.skrg.sekoraga.util.PaginationUtil;
import com.skrg.sekoraga.util.ResponseUtil;
import com.skrg.sekoraga.web.rest.errors.BadRequestAlertException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CAttachmentResource {
    private final Logger log = LoggerFactory.getLogger(CAttachmentResource.class);
    private static final String ENTITY_NAME = "cAttachment";

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${application.attachment.upload-dir}")
    private String uploadDir;

    @Autowired
    private CAttachmentService cAttachmentService;

    @Autowired
    private CAttachmentQueryService cAttachmentQueryService;

    @PostMapping("/c-attachments")
    public ResponseEntity<CAttachmentDTO> createCAttachment(@Valid @RequestBody CAttachmentDTO dto)
            throws URISyntaxException {
        log.debug("REST request to save CAttachment : {}", dto);
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new cAttachment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CAttachmentDTO result = cAttachmentService.save(dto);
        return ResponseEntity.created(new URI("/api/c-attachments/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
                .body(result);
    }

    @PutMapping("/c-attachments")
    public ResponseEntity<CAttachmentDTO> updateCAttachment(@Valid @RequestBody CAttachmentDTO dto)
            throws URISyntaxException {
        log.debug("REST request to update CAttachment : {}", dto);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CAttachmentDTO result = cAttachmentService.save(dto);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dto.getId()))
                .body(result);
    }

    @GetMapping("/c-attachments")
    public ResponseEntity<List<CAttachmentDTO>> getAllCAttachments(CAttachmentCriteria criteria, Pageable pageable) {
        log.debug("REST request to get a page of CAttachments");
        Page<CAttachmentDTO> page = cAttachmentQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/c-attachments/count")
    public ResponseEntity<Long> countCAttachments(CAttachmentCriteria criteria) {
        log.debug("REST request to count CAttachments by criteria: {}", criteria);
        return ResponseEntity.ok().body(cAttachmentQueryService.countByCriteria(criteria));
    }

    @GetMapping("/c-attachments/{id}")
    public ResponseEntity<CAttachmentDTO> getCAttachment(@PathVariable String id) {
        log.debug("REST request to get CAttachment : {}", id);
        Optional<CAttachmentDTO> dto = cAttachmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dto);
    }

    @DeleteMapping("/c-attachments/{id}")
    public ResponseEntity<Void> deleteCAttachment(@PathVariable String id) {
        log.debug("REST request to delete CAttachment : {}", id);
        cAttachmentService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id))
                .build();
    }

    // Upload endpoint
    @PostMapping("/c-attachments/upload")
    public ResponseEntity<CAttachmentDTO> uploadAttachment(@RequestParam("file") MultipartFile file,
            @RequestParam("type") String type,
            @RequestParam(value = "documentType", required = false) String documentType)
            throws IOException, URISyntaxException {
        log.debug("REST request to upload file: {}", file.getOriginalFilename());

        // Generate filename: datenow_namafile
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String originalName = file.getOriginalFilename();
        String safeName = (originalName != null) ? originalName.replaceAll("[^a-zA-Z0-9.\\-_]", "_") : "file";
        String storedFileName = now + "_" + safeName;
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        Path filePath = uploadPath.resolve(storedFileName);
        Files.copy(file.getInputStream(), filePath);

        CAttachmentDTO dto = new CAttachmentDTO();
        dto.setFileName(storedFileName);
        dto.setMimeType(file.getContentType());
        dto.setType(type);
        dto.setDocumentType(documentType);
        // Optionally, you can add file path to DTO if needed
        CAttachmentDTO result = cAttachmentService.save(dto);
        return ResponseEntity.created(new URI("/api/c-attachments/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
                .body(result);
    }

    @GetMapping("/c-attachments/{id}/download")
    public ResponseEntity<Resource> downloadAttachment(@PathVariable String id) throws IOException {
        log.debug("REST request to download attachment : {}", id);
        Optional<CAttachmentDTO> dtoOpt = cAttachmentService.findOne(id);
        if (dtoOpt.isEmpty() || dtoOpt.get().getFileName() == null) {
            return ResponseEntity.notFound().build();
        }
        String fileName = dtoOpt.get().getFileName();
        Path filePath = Paths.get(uploadDir).resolve(fileName);
        if (!Files.exists(filePath)) {
            return ResponseEntity.notFound().build();
        }
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(filePath));
        String contentType = Files.probeContentType(filePath);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + fileName)
                .contentType(MediaType.parseMediaType(contentType))
                .contentLength(Files.size(filePath))
                .body(resource);
    }
}
