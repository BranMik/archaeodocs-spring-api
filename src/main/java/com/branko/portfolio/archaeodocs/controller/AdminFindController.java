package com.branko.portfolio.archaeodocs.controller;

import com.branko.portfolio.archaeodocs.dto.FindCompleteResponseDTO;
import com.branko.portfolio.archaeodocs.dto.FindCreateDTO;
import com.branko.portfolio.archaeodocs.dto.FindResponseDTO;
import com.branko.portfolio.archaeodocs.repository.FindRepository;
import com.branko.portfolio.archaeodocs.service.FindService;
import com.branko.portfolio.archaeodocs.service.ImageSiteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/finds")
@AllArgsConstructor
public class AdminFindController {

    private final FindRepository repo;
    private final FindService findService;
    private static final Logger log =
            LoggerFactory.getLogger(AdminFindController.class);

    @PostMapping
    public ResponseEntity<FindResponseDTO> create(@Valid @RequestBody FindCreateDTO dto) {
        log.info("In create, dto = "+dto);
        FindResponseDTO created = findService.createNewFind(dto);
        log.info("dto created, now Response. dto = "+created);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
