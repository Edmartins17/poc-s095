package br.com.bnb.dto;

import java.time.LocalDateTime;

public class BrandResponseDTO extends  BasicRequestDTO {

    private Long id;
    private String name;

    public BrandResponseDTO(Long id, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.name = name;
    }
}
