package com.cup.breedhorse.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cup.breedhorse.dto.BreedHorseResponse;
import com.cup.breedhorse.dto.PhotoResponse;
import com.cup.breedhorse.mapper.BreedHorseMapper;
import com.cup.breedhorse.model.BreedHorse;
import com.cup.breedhorse.model.Characteristic;
import com.cup.breedhorse.repository.BreedHorseRepository;
import com.cup.breedhorse.repository.CharacteristicRepository;
import com.cup.breedhorse.repository.PhotosRepository;
import com.cup.breedhorse.service.BreedHorseService;

import org.springframework.transaction.annotation.Transactional;

@Service
public class BreedHorseServiceImpl implements BreedHorseService {

    private final BreedHorseRepository breedHorseRepository;
    private final CharacteristicRepository characteristicRepository;
    private final PhotosRepository photosRepository;
    private final BreedHorseMapper breedHorseMapper;

    public BreedHorseServiceImpl(BreedHorseRepository breedHorseRepository,
                                   CharacteristicRepository characteristicRepository,
                                   PhotosRepository photosRepository,
                                   BreedHorseMapper breedHorseMapper) {
        this.breedHorseRepository = breedHorseRepository;
        this.characteristicRepository = characteristicRepository;
        this.photosRepository = photosRepository;
        this.breedHorseMapper = breedHorseMapper;
    }

    @Override
    @Transactional
    public BreedHorseResponse create(BreedHorseResponse request) {
        BreedHorse breedHorse = BreedHorse.builder()
            .name(request.name())
            .shortDescription(request.shortDescription())
            .averageHeight(request.averageHeight())
            .averageWeight(request.averageWeight())
            .history(request.history())
            .originCountry(request.originCountry())
            .active(request.active() == null ? Boolean.TRUE : request.active())
            .build();

            if(request.characteristics() != null) {
                Set<Characteristic> chars = request.characteristics().stream()
                    .map(name -> characteristicRepository.findByNameIgnoreCase(name)
                        .orElseGet(() -> Characteristic.builder().name(name).build()))
                    .collect(Collectors.toSet());
                breedHorse.setCharacteristics(chars);
            }

        BreedHorse saved = breedHorseRepository.save(breedHorse);
        return breedHorseMapper.toDto(saved);
    }

    @Override
    @Transactional
    public BreedHorseResponse update(Long id, BreedHorseResponse request) {
        BreedHorse breedHorse = breedHorseRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid breed horse ID"));

        breedHorse.setName(request.name());
        breedHorse.setShortDescription(request.shortDescription());
        breedHorse.setAverageHeight(request.averageHeight());
        breedHorse.setAverageWeight(request.averageWeight());
        breedHorse.setHistory(request.history());
        breedHorse.setOriginCountry(request.originCountry());
        breedHorse.setActive(request.active());

        if (request.characteristics() != null) {
            Set<Characteristic> chars = request.characteristics().stream()
                .map(name -> characteristicRepository.findByNameIgnoreCase(name)
                    .orElseGet(() -> Characteristic.builder().name(name).build()))
                .collect(Collectors.toSet());
            breedHorse.setCharacteristics(chars);
        }

        BreedHorse saved = breedHorseRepository.save(breedHorse);
        return breedHorseMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        BreedHorse breedHorse = breedHorseRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid breed horse ID"));

        breedHorseRepository.delete(breedHorse);
    }

    @Override
    @Transactional(readOnly = true)
    public BreedHorseResponse findById(Long id) {
        BreedHorse breedHorse = breedHorseRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid breed horse ID"));
        return breedHorseMapper.toDto(breedHorse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BreedHorseResponse> list(Pageable pageable, String q, String origin, String characteristic) {
        Page<BreedHorse> page = breedHorseRepository.findAll(pageable);
        List<BreedHorseResponse> dtoList = page.getContent().stream().map(breedHorseMapper::toDto).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, page.getTotalElements());
    }
}
