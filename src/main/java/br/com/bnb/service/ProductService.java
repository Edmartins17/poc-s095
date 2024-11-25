package br.com.bnb.service;

import br.com.bnb.dto.ProductRequestDTO;
import br.com.bnb.model.Product;
import br.com.bnb.dto.ProductResponseDTO;
import br.com.bnb.repository.ProductRepository;
import br.com.bnb.repository.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Busca produtos com filtros opcionais e paginação.
     */
    public Page<ProductResponseDTO> getProducts(ProductSpecification specification, Pageable pageable) {
        return productRepository.findAll(specification, pageable)
                .map(product -> new ProductResponseDTO(
                        product.getId(),
                        product.getName(),
                        product.getBrand().getName(),
                        product.getAuthor().getName(),
                        product.getPrice(),
                        product.getDescription()
                ));
    }

    /**
     * Busca um produto por ID.
     */
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + id));

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getBrand().getName(),
                product.getAuthor().getName(),
                product.getPrice(),
                product.getDescription()
        );
    }

    /**
     * Cria um novo produto.
     */
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setDescription(productRequestDTO.getDescription());
        product.setBrand(productRequestDTO.getBrand());
        product.setAuthor(productRequestDTO.getAuthor());

        Product savedProduct = productRepository.save(product);

        return new ProductResponseDTO(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getBrand().getName(),
                savedProduct.getAuthor().getName(),
                savedProduct.getPrice(),
                savedProduct.getDescription()
        );
    }

    /**
     * Atualiza completamente um produto.
     */
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + id));

        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setDescription(productRequestDTO.getDescription());
        product.setBrand(productRequestDTO.getBrand());
        product.setAuthor(productRequestDTO.getAuthor());

        Product updatedProduct = productRepository.save(product);

        return new ProductResponseDTO(
                updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getBrand().getName(),
                updatedProduct.getAuthor().getName(),
                updatedProduct.getPrice(),
                updatedProduct.getDescription()
        );
    }

    /**
     * Atualiza parcialmente um produto.
     */
    public ProductResponseDTO partiallyUpdateProduct(Long id, ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + id));

        if (productRequestDTO.getName() != null) {
            product.setName(productRequestDTO.getName());
        }
        if (productRequestDTO.getPrice() != null) {
            product.setPrice(productRequestDTO.getPrice());
        }
        if (productRequestDTO.getDescription() != null) {
            product.setDescription(productRequestDTO.getDescription());
        }
        if (productRequestDTO.getBrand() != null) {
            product.setBrand(productRequestDTO.getBrand());
        }
        if (productRequestDTO.getAuthor() != null) {
            product.setAuthor(productRequestDTO.getAuthor());
        }

        Product updatedProduct = productRepository.save(product);

        return new ProductResponseDTO(
                updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getBrand().getName(),
                updatedProduct.getAuthor().getName(),
                updatedProduct.getPrice(),
                updatedProduct.getDescription()
        );
    }

    /**
     * Exclui um produto pelo ID.
     */
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Produto não encontrado com ID: " + id);
        }
        productRepository.deleteById(id);
    }
}

