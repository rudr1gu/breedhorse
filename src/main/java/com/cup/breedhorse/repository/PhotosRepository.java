package com.cup.breedhorse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cup.breedhorse.model.Photos;

public interface PhotosRepository extends JpaRepository<Photos, Long> {
    Optional<Photos> findByNameIgnoreCase(String name);
}
