package com.cup.breedhorse.mapper;

import java.util.stream.Collectors;

import com.cup.breedhorse.dto.BreedHorseResponse;
import com.cup.breedhorse.model.BreedHorse;
import com.cup.breedhorse.model.Characteristic;

public class BreedHorseMapper {

    public BreedHorseResponse toDto(BreedHorse breedHorse) {
        if (breedHorse == null) {
            return null;
        }

        return BreedHorseResponse.builder()
                .id(breedHorse.getId())
                .name(breedHorse.getName())
                .shortDescription(breedHorse.getShortDescription())
                .averageHeight(breedHorse.getAverageHeight())
                .averageWeight(breedHorse.getAverageWeight())
                .history(breedHorse.getHistory())
                .originCountry(breedHorse.getOriginCountry())
                .active(breedHorse.getActive())
                .characteristics(breedHorse.getCharacteristics().stream()
                        .map(Characteristic::getName)
                        .collect(Collectors.toSet()))
                .build();
    }

}
