package br.com.bnb.dto;

import br.com.bnb.model.Author;
import br.com.bnb.model.Brand;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ProductRequestDTO {

    @NotBlank(message = "O nome do produto é obrigatório.")
    @Size(max = 255, message = "O nome do produto deve ter no máximo 255 caracteres.")
    private String name;

    @NotNull(message = "O preço do produto é obrigatório.")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço do produto deve ser maior que zero.")
    private BigDecimal price;

    @Size(max = 1000, message = "A descrição do produto deve ter no máximo 1000 caracteres.")
    private String description;

    @NotNull(message = "A marca do produto é obrigatória.")
    private Brand brand;

    @NotNull(message = "O autor do produto é obrigatório.")
    private Author author;

    // Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}

