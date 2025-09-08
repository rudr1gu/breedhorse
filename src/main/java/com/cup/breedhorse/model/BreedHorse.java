package com.cup.breedhorse.model;


import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    private String averageHeight;
    private String averageWeight;

    @Lob
    private String history;

    @Builder.Default
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
        name = "breed_characteristics",
        joinColumns = @JoinColumn(name = "breed_id"),
        inverseJoinColumns = @JoinColumn(name = "characteristic_id")
    )
    private Set<Characteristic> characteristics = new HashSet<>();

    private String originCountry;
    @Builder.Default
    private Boolean active = true;

    @Column(updatable = false)
    @Builder.Default
    private Instant createdAt = Instant.now();

    private Instant updatedAt;
}
