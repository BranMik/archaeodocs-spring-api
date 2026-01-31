package com.branko.portfolio.archaeodocs.service;

import com.branko.portfolio.archaeodocs.domain.Material;
import com.branko.portfolio.archaeodocs.dto.MaterialResponseDTO;
import com.branko.portfolio.archaeodocs.mapper.MaterialMapper;
import com.branko.portfolio.archaeodocs.repository.MaterialRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper mapper;

    @Transactional(readOnly = true)
    public List<String> getAllLabels() {
        return materialRepository.findAllLabels();
    }

    @Transactional(readOnly = true)
    public List<MaterialResponseDTO> getAllMaterials(){
        return materialRepository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public MaterialResponseDTO getMaterialById(Long id){
        return materialRepository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Material not found: " + id));
    }

    @Transactional(readOnly = true)
    public String getMaterialLabelById(Long id){
        return materialRepository.findById(id)
                .map(Material::getLabel)
                .orElseThrow(() -> new EntityNotFoundException("Material not found: " + id));
    }
}
