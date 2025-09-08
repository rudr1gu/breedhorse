package com.cup.breedhorse.dto;

public record PhotoResponse(
    Long id,
    String fileName,
    String url,
    String contentType,
    Long size,
    Integer displayOrder
) {}
