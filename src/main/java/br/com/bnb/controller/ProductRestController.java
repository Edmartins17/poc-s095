package br.com.bnb.controller;

import br.com.bnb.dto.ProductRequestDTO;
import br.com.bnb.dto.ProductResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/product")
@Tag(name = "Products", description = "API para gerenciamento de produtos")
public interface ProductRestController {

    @Operation(summary = "Buscar produtos", description = "Busca todos os produtos com filtros opcionais e paginação.")
    @GetMapping
    Page<ProductResponseDTO> getAllProducts(
            @Parameter(description = "Nome do produto para filtro") @RequestParam(required = false) String name,
            @Parameter(description = "Nome da marca para filtro") @RequestParam(required = false) String brandName,
            @Parameter(description = "Nome do autor para filtro") @RequestParam(required = false) String authorName,
            @Parameter(description = "Preço mínimo para filtro") @RequestParam(required = false) Double minPrice,
            @Parameter(description = "Preço máximo para filtro") @RequestParam(required = false) Double maxPrice,
            @Parameter(hidden = true) Pageable pageable
    );

    @Operation(summary = "Buscar produto por ID", description = "Retorna os detalhes de um produto específico pelo ID.")
    @GetMapping("/{id}")
    ProductResponseDTO getProductById(@Parameter(description = "ID do produto") @PathVariable Long id);

    @Operation(summary = "Criar produto", description = "Cria um novo produto no sistema.")
    @PostMapping
    ProductResponseDTO createProduct(@RequestBody ProductRequestDTO productRequestDTO);

    @Operation(summary = "Atualizar produto", description = "Atualiza completamente os dados de um produto pelo ID.")
    @PutMapping("/{id}")
    ProductResponseDTO updateProduct(
            @Parameter(description = "ID do produto") @PathVariable Long id,
            @RequestBody ProductRequestDTO productRequestDTO
    );

    @Operation(summary = "Atualizar parcialmente produto", description = "Atualiza parcialmente os dados de um produto pelo ID.")
    @PatchMapping("/{id}")
    ProductResponseDTO partiallyUpdateProduct(
            @Parameter(description = "ID do produto") @PathVariable Long id,
            @RequestBody ProductRequestDTO productRequestDTO
    );

    @Operation(summary = "Excluir produto", description = "Exclui um produto pelo ID.")
    @DeleteMapping("/{id}")
    void deleteProduct(@Parameter(description = "ID do produto") @PathVariable Long id);
}


