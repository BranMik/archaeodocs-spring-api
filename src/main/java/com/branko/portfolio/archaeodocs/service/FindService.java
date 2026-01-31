package com.branko.portfolio.archaeodocs.service;

import com.branko.portfolio.archaeodocs.domain.Find;
import com.branko.portfolio.archaeodocs.dto.FindCompleteResponseDTO;
import com.branko.portfolio.archaeodocs.dto.FindCreateDTO;
import com.branko.portfolio.archaeodocs.dto.FindResponseDTO;
import com.branko.portfolio.archaeodocs.mapper.FindMapper;
import com.branko.portfolio.archaeodocs.repository.FindRepository;
import com.branko.portfolio.archaeodocs.repository.MaterialRepository;
import com.branko.portfolio.archaeodocs.repository.PeriodRepository;
import com.branko.portfolio.archaeodocs.repository.SiteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindService {

    private final FindRepository findRepository;
    private final FindMapper findMapper;
    private final MaterialRepository materialRepository;
    private final PeriodRepository periodRepository;
    private final SiteRepository siteRepository;

    @Transactional
    public FindResponseDTO createNewFind(FindCreateDTO dto){
        Find find = new Find();
        find.setLabel(dto.getLabel());
        find.setMaterial(materialRepository.findByLabel(dto.getMaterial()).getFirst());
        find.setPeriod(periodRepository.findByName(dto.getPeriod()).getFirst());
        find.setSite(siteRepository.getReferenceById(dto.getSiteId()));

        Find saved = findRepository.save(find);

        return findMapper.toResponseDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<FindResponseDTO> getFindsBySiteId(Long siteId) {
        return findRepository.findFindsBySiteId(siteId)
                .stream()
                .map(findMapper::fromViewToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public FindCompleteResponseDTO getFindById(Long findId){
        return findRepository.findById(findId)
                            .map(findMapper::toCompleteResponseDTO)
                            .orElseThrow(() -> new EntityNotFoundException("Find not found: " + findId));
    }

    @Transactional(readOnly = true)
    public List<FindResponseDTO> getAllFinds(){
        return findRepository.findAll()
                .stream()
                .map(findMapper::toResponseDTO)
                .toList();
    }
}
