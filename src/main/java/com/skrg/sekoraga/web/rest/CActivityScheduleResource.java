package com.skrg.sekoraga.web.rest;

import com.skrg.sekoraga.service.CActivityScheduleService;
import com.skrg.sekoraga.service.dto.CActivityScheduleDTO;
import com.skrg.sekoraga.service.query.CActivityScheduleQueryService;
import com.skrg.sekoraga.service.criteria.CActivityScheduleCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/c-activity-schedules")
public class CActivityScheduleResource {
    private final CActivityScheduleService service;
    private final CActivityScheduleQueryService queryService;

    public CActivityScheduleResource(CActivityScheduleService service, CActivityScheduleQueryService queryService) {
        this.service = service;
        this.queryService = queryService;
    }

    @GetMapping
    public ResponseEntity<List<CActivityScheduleDTO>> getAll() {
        List<CActivityScheduleDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CActivityScheduleDTO>> getAllPage(CActivityScheduleCriteria criteria,
            Pageable pageable) {
        Page<CActivityScheduleDTO> page = queryService.findByCriteria(criteria, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CActivityScheduleDTO> getById(@PathVariable Integer id) {
        Optional<CActivityScheduleDTO> dto = service.findById(id);
        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CActivityScheduleDTO> create(@RequestBody CActivityScheduleDTO dto)
            throws URISyntaxException {
        CActivityScheduleDTO result = service.save(dto);
        return ResponseEntity.created(new URI("/api/c-activity-schedules/" + result.getScheduleId())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CActivityScheduleDTO> update(@PathVariable Integer id,
            @RequestBody CActivityScheduleDTO dto) {
        dto.setScheduleId(id);
        CActivityScheduleDTO result = service.save(dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<CActivityScheduleDTO>> getByUserId(@PathVariable Long userId) {
        List<CActivityScheduleDTO> list = service.findByUserId(userId);
        return ResponseEntity.ok(list);
    }
}
