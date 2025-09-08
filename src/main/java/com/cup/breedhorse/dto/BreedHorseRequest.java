package com.cup.breedhorse.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BreedHorseRequest(
    @NotBlank
    @Size(max = 255)
    String name,
    String shortDescription,
    String averageHeight,
    String averageWeight,
    String history,
    String originCountry,
    Set<String> characteristics,
    Boolean active
) {}