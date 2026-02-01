package com.branko.portfolio.archaeodocs.controller;

import com.branko.portfolio.archaeodocs.dto.FindCompleteResponseDTO;
import com.branko.portfolio.archaeodocs.dto.FindCreateDTO;
import com.branko.portfolio.archaeodocs.dto.FindResponseDTO;
import com.branko.portfolio.archaeodocs.repository.FindRepository;
import com.branko.portfolio.archaeodocs.service.FindService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/finds")
@AllArgsConstructor
public class FindController {

    private final FindRepository repo;
    private final FindService findService;

    @GetMapping
    public List<FindResponseDTO> all() {
        return findService.getAllFinds();
    }

    @PostMapping
    public ResponseEntity<FindResponseDTO> create(@Valid @RequestBody FindCreateDTO dto) {
        FindResponseDTO created = findService.createNewFind(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindCompleteResponseDTO> one(@PathVariable Long id) {
        FindCompleteResponseDTO found = findService.getFindById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(found);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
