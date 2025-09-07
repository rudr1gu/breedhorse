package com.cup.breedhorse.model;


import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "breed_horses")
public class BreedHorse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 100)
    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 1000)
    private String shortDescription;

    @Lob
    private String history;

    private String originCountry;
    private Boolean active = true;

    @Column(updatable = false)
    private Instant createdAt = Instant.now();

    private Instant updatedAt;
}
