package com.skrg.sekoraga.web.rest;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.skrg.sekoraga.domain.AdUser;
import com.skrg.sekoraga.domain.criteria.AdUserCriteria;
import com.skrg.sekoraga.domain.dto.AdUserDTO;
import com.skrg.sekoraga.service.AdUserQueryService;
import com.skrg.sekoraga.service.AdUserService;
import com.skrg.sekoraga.util.HeaderUtil;
import com.skrg.sekoraga.util.PaginationUtil;
import com.skrg.sekoraga.util.ResponseUtil;
import com.skrg.sekoraga.web.rest.errors.BadRequestAlertException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AdUserResource {
    private final Logger log = LoggerFactory.getLogger(AdUserResource.class);
    private static final String ENTITY_NAME = "adUser";

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private AdUserService adUserService;

    @Autowired
    private AdUserQueryService adUserQueryService;

    @PostMapping("/ad-users")
    public ResponseEntity<AdUserDTO> createAdUser(@Valid @RequestBody AdUserDTO adUserDTO) throws URISyntaxException {
        log.debug("REST request to save AdUser : {}", adUserDTO);
        if (adUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new adUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdUserDTO result = adUserService.save(adUserDTO);
        return ResponseEntity.created(new URI("/api/ad-users/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
                .body(result);
    }

    @PutMapping("/ad-users")
    public ResponseEntity<AdUserDTO> updateAdUser(@Valid @RequestBody AdUserDTO adUserDTO) throws URISyntaxException {
        log.debug("REST request to update AdUser : {}", adUserDTO);
        if (adUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdUserDTO result = adUserService.save(adUserDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adUserDTO.getId()))
                .body(result);
    }

    @GetMapping("/ad-users")
    public ResponseEntity<List<AdUserDTO>> getAllAdUsers(AdUserCriteria criteria, Pageable pageable) {
        log.debug("REST request to get a page of AdUsers");
        Page<AdUserDTO> page = adUserQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/ad-users/count")
    public ResponseEntity<Long> countAdUsers(AdUserCriteria criteria) {
        log.debug("REST request to count AdUsers by criteria: {}", criteria);
        return ResponseEntity.ok().body(adUserQueryService.countByCriteria(criteria));
    }

    @GetMapping("/ad-users/{id}")
    public ResponseEntity<AdUserDTO> getAdUser(@PathVariable String id) {
        log.debug("REST request to get AdUser : {}", id);
        Optional<AdUserDTO> adUserDTO = adUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adUserDTO);
    }

    @DeleteMapping("/ad-users/{id}")
    public ResponseEntity<Void> deleteAdUser(@PathVariable String id) {
        log.debug("REST request to delete AdUser : {}", id);
        adUserService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id))
                .build();
    }

    @GetMapping("/ad-users/me")
    public ResponseEntity<AdUserDTO> getCurrentUser(org.springframework.security.core.Authentication authentication) {
        String username = authentication.getName();
        Optional<AdUser> userOpt = adUserService.getAdUserRepository().findFirstByUsername(username);
        if (userOpt.isPresent()) {
            AdUserDTO dto = adUserService.getAdUserMapper().toDto(userOpt.get());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }
}
