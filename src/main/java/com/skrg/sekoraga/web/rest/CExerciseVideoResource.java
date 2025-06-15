package com.skrg.sekoraga.web.rest;

import com.skrg.sekoraga.service.CExerciseVideoService;
import com.skrg.sekoraga.service.dto.CExerciseVideoDTO;
import com.skrg.sekoraga.service.query.CExerciseVideoQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/c-exercise-videos")
public class CExerciseVideoResource {
    private final CExerciseVideoService service;
    private final CExerciseVideoQueryService queryService;

    public CExerciseVideoResource(CExerciseVideoService service, CExerciseVideoQueryService queryService) {
        this.service = service;
        this.queryService = queryService;
    }

    @GetMapping
    public ResponseEntity<List<CExerciseVideoDTO>> getAll() {
        List<CExerciseVideoDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CExerciseVideoDTO>> getAllPage(Pageable pageable) {
        Page<CExerciseVideoDTO> page = queryService.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CExerciseVideoDTO> getById(@PathVariable Long id) {
        Optional<CExerciseVideoDTO> dto = service.findById(id);
        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CExerciseVideoDTO> create(@RequestBody CExerciseVideoDTO dto) throws URISyntaxException {
        CExerciseVideoDTO result = service.save(dto);
        return ResponseEntity.created(new URI("/api/c-exercise-videos/" + result.getVideoId())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CExerciseVideoDTO> update(@PathVariable Long id, @RequestBody CExerciseVideoDTO dto) {
        dto.setVideoId(id);
        CExerciseVideoDTO result = service.save(dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
