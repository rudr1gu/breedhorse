package com.cup.breedhorse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cup.breedhorse.model.BreedHorse;

public interface BreedHorseRepository extends JpaRepository<BreedHorse, Long> {

    Optional<BreedHorse> findByNameIgnoreCase(String name);

}