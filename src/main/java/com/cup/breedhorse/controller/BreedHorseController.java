package com.cup.breedhorse.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.data.domain.Pageable;

import com.cup.breedhorse.dto.BreedHorseResponse;
import com.cup.breedhorse.service.BreedHorseService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/breed-horses")
public class BreedHorseController {

    @Autowired
    private BreedHorseService breedHorseService;

    @GetMapping
    public ResponseEntity<Page<BreedHorseResponse>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String origin,
            @RequestParam(required = false) String characteristic
            ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BreedHorseResponse> breedHorses = breedHorseService.list(pageable, q, origin, characteristic);
        return ResponseEntity.ok(breedHorses);
    }

    @PostMapping
    public ResponseEntity<BreedHorseResponse> create(@Valid @RequestBody BreedHorseResponse request) {
        BreedHorseResponse created = breedHorseService.create(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();
        return ResponseEntity.created(uri).body(created);
    }
    
}
