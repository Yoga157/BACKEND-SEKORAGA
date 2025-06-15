package com.skrg.sekoraga.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.skrg.sekoraga.domain.criteria.AdAuthorityCriteria;
import com.skrg.sekoraga.domain.dto.AdAuthorityDTO;
import com.skrg.sekoraga.service.AdAuthorityQueryService;
import com.skrg.sekoraga.service.AdAuthorityService;
import com.skrg.sekoraga.util.HeaderUtil;
import com.skrg.sekoraga.util.PaginationUtil;
import com.skrg.sekoraga.util.ResponseUtil;
import com.skrg.sekoraga.web.rest.errors.BadRequestAlertException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AdAuthorityResource {

    private final Logger log = LoggerFactory.getLogger(AdAuthorityResource.class);

    private static final String ENTITY_NAME = "authority";

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private AdAuthorityService authorityService;

    @Autowired
    private AdAuthorityQueryService authorityQueryService;

    @PostMapping("/authorities")
    public ResponseEntity<AdAuthorityDTO> createAuthority(@Valid @RequestBody AdAuthorityDTO authorityDTO)
            throws URISyntaxException {
        log.debug("REST request to save Authority : {}", authorityDTO);
        if (authorityDTO.getId() != null) {
            throw new BadRequestAlertException("A new authority cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdAuthorityDTO result = authorityService.save(authorityDTO);
        return ResponseEntity.created(new URI("/api/authorities/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME,
                        result.getId().toString()))
                .body(result);
    }

    @PutMapping("/authorities")
    public ResponseEntity<AdAuthorityDTO> updateAuthority(@Valid @RequestBody AdAuthorityDTO authorityDTO)
            throws URISyntaxException {
        log.debug("REST request to update Authority : {}", authorityDTO);
        if (authorityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdAuthorityDTO result = authorityService.save(authorityDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                        authorityDTO.getId().toString()))
                .body(result);
    }

    @GetMapping("/authorities")
    public ResponseEntity<List<AdAuthorityDTO>> getAllAuthorities(AdAuthorityCriteria criteria, Pageable pageable) {
        log.debug("REST request to get a page of Authorities");
        Page<AdAuthorityDTO> page = authorityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/authorities/count")
    public ResponseEntity<Long> countAuthorities(AdAuthorityCriteria criteria) {
        log.debug("REST request to count Authorities by criteria: {}", criteria);
        return ResponseEntity.ok().body(authorityQueryService.countByCriteria(criteria));
    }

    @GetMapping("/authorities/{id}")
    public ResponseEntity<AdAuthorityDTO> getAuthority(@PathVariable String id) {
        log.debug("REST request to get Authority : {}", id);
        Optional<AdAuthorityDTO> authorityDTO = authorityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(authorityDTO);
    }

    @DeleteMapping("/authorities/{id}")
    public ResponseEntity<Void> deleteAuthority(@PathVariable String id) {
        log.debug("REST request to delete Authority : {}", id);
        authorityService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id))
                .build();
    }
}
