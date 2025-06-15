package com.skrg.sekoraga.web.rest;

import com.skrg.sekoraga.service.CExerciseCategoryService;
import com.skrg.sekoraga.service.dto.CExerciseCategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/c-exercise-categories")
public class CExerciseCategoryResource {
    private final CExerciseCategoryService service;

    public CExerciseCategoryResource(CExerciseCategoryService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CExerciseCategoryDTO> getById(@PathVariable Long id) {
        Optional<CExerciseCategoryDTO> dto = service.findById(id);
        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CExerciseCategoryDTO>> getAll() {
        List<CExerciseCategoryDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<CExerciseCategoryDTO> create(@RequestBody CExerciseCategoryDTO dto) {
        CExerciseCategoryDTO result = service.save(dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CExerciseCategoryDTO> update(@PathVariable Long id, @RequestBody CExerciseCategoryDTO dto) {
        dto.setCategoryId(id);
        CExerciseCategoryDTO result = service.save(dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
