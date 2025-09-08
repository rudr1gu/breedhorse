package com.cup.breedhorse.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cup.breedhorse.dto.BreedHorseResponse;

public interface BreedHorseService {
    BreedHorseResponse create(BreedHorseResponse request);
    BreedHorseResponse update(Long id, BreedHorseResponse request);
    void delete(Long id);
    BreedHorseResponse findById(Long id);
    Page<BreedHorseResponse> list(Pageable pageable, String q, String origin, String characteristic);
}
