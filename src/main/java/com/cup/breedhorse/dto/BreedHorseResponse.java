package com.cup.breedhorse.dto;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public record BreedHorseResponse(
    Long id,
    String name,
    String shortDescription,
    String averageHeight,
    String averageWeight,
    String history,
    String originCountry,
    Boolean active,
    Instant createdAt,
    Instant updatedAt,
    List<PhotoResponse> photos,
    Set<String> characteristics
) {}
