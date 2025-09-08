package com.cup.breedhorse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cup.breedhorse.model.Characteristic;

public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {
    Optional<Characteristic> findByNameIgnoreCase(String name);

}
