package com.branko.portfolio.archaeodocs.controller;

import com.branko.portfolio.archaeodocs.dto.FindCompleteResponseDTO;
import com.branko.portfolio.archaeodocs.dto.FindResponseDTO;
import com.branko.portfolio.archaeodocs.service.FindService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/finds")
@AllArgsConstructor
public class PublicFindController {

    private final FindService findService;

    @GetMapping
    public List<FindResponseDTO> all() {
        return findService.getAllFinds();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindCompleteResponseDTO> one(@PathVariable Long id) {
        FindCompleteResponseDTO found = findService.getFindById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(found);
    }
}
