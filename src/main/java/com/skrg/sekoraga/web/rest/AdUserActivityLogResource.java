package com.skrg.sekoraga.web.rest;

import com.skrg.sekoraga.service.AdUserActivityLogService;
import com.skrg.sekoraga.service.dto.AdUserActivityLogDTO;
import com.skrg.sekoraga.service.query.AdUserActivityLogQueryService;
import com.skrg.sekoraga.service.criteria.AdUserActivityLogCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ad-user-activity-logs")
public class AdUserActivityLogResource {
    private final AdUserActivityLogService service;
    private final AdUserActivityLogQueryService queryService;

    public AdUserActivityLogResource(AdUserActivityLogService service, AdUserActivityLogQueryService queryService) {
        this.service = service;
        this.queryService = queryService;
    }

    @GetMapping
    public ResponseEntity<List<AdUserActivityLogDTO>> getAll() {
        List<AdUserActivityLogDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<AdUserActivityLogDTO>> getAllPage(AdUserActivityLogCriteria criteria,
            Pageable pageable) {
        Page<AdUserActivityLogDTO> page = queryService.findByCriteria(criteria, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdUserActivityLogDTO> getById(@PathVariable Integer id) {
        Optional<AdUserActivityLogDTO> dto = service.findById(id);
        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AdUserActivityLogDTO> create(@RequestBody AdUserActivityLogDTO dto)
            throws URISyntaxException {
        AdUserActivityLogDTO result = service.save(dto);
        return ResponseEntity.created(new URI("/api/ad-user-activity-logs/" + result.getLogId())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdUserActivityLogDTO> update(@PathVariable Integer id,
            @RequestBody AdUserActivityLogDTO dto) {
        dto.setLogId(id);
        AdUserActivityLogDTO result = service.save(dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<AdUserActivityLogDTO>> findByUserId(@PathVariable Long userId) {
        List<AdUserActivityLogDTO> list = service.findByUserId(userId);
        return ResponseEntity.ok(list);
    }
}
