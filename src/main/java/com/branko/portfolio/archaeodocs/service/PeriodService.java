package com.branko.portfolio.archaeodocs.service;

import com.branko.portfolio.archaeodocs.domain.Period;
import com.branko.portfolio.archaeodocs.dto.PeriodResponseDTO;
import com.branko.portfolio.archaeodocs.mapper.PeriodMapper;
import com.branko.portfolio.archaeodocs.repository.PeriodRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PeriodService {

    private final PeriodRepository periodRepository;
    private final PeriodMapper periodMapper;

    @Transactional(readOnly = true)
    public PeriodResponseDTO getPeriodById(Long id) {
        return periodRepository.findById(id)
                .map(periodMapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Period not found: " + id));
    }

    @Transactional(readOnly = true)
    public String getOnePeriodName(Long id) {
        return periodRepository.findById(id)
                .map(Period::getName)
                .orElseThrow(() -> new EntityNotFoundException("Period not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<String> getAllPeriodNames() {
        return periodRepository.findAllNames();
    }

    @Transactional(readOnly = true)
    public List<PeriodResponseDTO> getAllPeriods() {
        return periodRepository.findAll()
                .stream()
                .map(periodMapper::toResponseDTO)
                .toList();
    }
}
