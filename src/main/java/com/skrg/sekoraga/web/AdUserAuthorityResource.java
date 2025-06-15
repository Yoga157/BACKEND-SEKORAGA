package com.skrg.sekoraga.web;

import com.skrg.sekoraga.service.AdUserAuthorityService;
import com.skrg.sekoraga.service.AdUserAuthorityQueryService;
import com.skrg.sekoraga.service.dto.AdUserAuthorityDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ad-user-authorities")
public class AdUserAuthorityResource {
    private final AdUserAuthorityService service;
    private final AdUserAuthorityQueryService queryService;

    public AdUserAuthorityResource(AdUserAuthorityService service, AdUserAuthorityQueryService queryService) {
        this.service = service;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<AdUserAuthorityDTO> create(@RequestBody AdUserAuthorityDTO dto) {
        AdUserAuthorityDTO result = service.save(dto);
        return ResponseEntity
                .created(URI.create("/api/ad-user-authorities/" + result.getUserId() + "/" + result.getAuthorityId()))
                .body(result);
    }

    @PutMapping
    public ResponseEntity<AdUserAuthorityDTO> update(@RequestBody AdUserAuthorityDTO dto) {
        AdUserAuthorityDTO result = service.save(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<AdUserAuthorityDTO>> getAll() {
        List<AdUserAuthorityDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<AdUserAuthorityDTO>> getAllPaged(Pageable pageable) {
        Page<AdUserAuthorityDTO> page = queryService.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{userId}/{authorityId}")
    public ResponseEntity<AdUserAuthorityDTO> getOne(@PathVariable Long userId, @PathVariable Long authorityId) {
        Optional<AdUserAuthorityDTO> dto = service.findOne(userId, authorityId);
        return dto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{userId}/{authorityId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId, @PathVariable Long authorityId) {
        service.delete(userId, authorityId);
        return ResponseEntity.noContent().build();
    }
}
