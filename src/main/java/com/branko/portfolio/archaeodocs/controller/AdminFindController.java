package com.branko.portfolio.archaeodocs.controller;

import com.branko.portfolio.archaeodocs.dto.FindCreateDTO;
import com.branko.portfolio.archaeodocs.dto.FindResponseDTO;
import com.branko.portfolio.archaeodocs.repository.FindRepository;
import com.branko.portfolio.archaeodocs.service.FindService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin/finds")
@AllArgsConstructor
public class AdminFindController {

    private final FindRepository repo;
    private final FindService findService;

    @PostMapping
    public ResponseEntity<FindResponseDTO> create(@Valid @RequestBody FindCreateDTO dto) {
        FindResponseDTO created = findService.createNewFind(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
