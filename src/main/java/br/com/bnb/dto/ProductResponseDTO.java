package br.com.bnb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String brandName;
    private String authorName;
    private BigDecimal price;
    private String description;

    public ProductResponseDTO(Long id, String name, String brandName, String authorName, BigDecimal price, String description) {
        this.id = id;
        this.name = name;
        this.brandName = brandName;
        this.authorName = authorName;
        this.price = price;
        this.description = description;
    }

}
