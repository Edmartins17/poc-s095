package br.com.bnb.service;

import br.com.bnb.dto.ProductRequestDTO;
import br.com.bnb.dto.ProductResponseDTO;
import br.com.bnb.model.Author;
import br.com.bnb.model.Brand;
import br.com.bnb.model.Product;
import br.com.bnb.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    public ProductServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProducts() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product A");
        product1.setPrice(BigDecimal.valueOf(100));
        product1.setDescription("Description A");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product B");
        product2.setPrice(BigDecimal.valueOf(200));
        product2.setDescription("Description B");

        when(productRepository.findAll((Specification<Product>) any(), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(product1, product2)));

        Page<ProductResponseDTO> products = productService.getProducts(null, Pageable.unpaged());

        assertThat(products.getContent()).hasSize(2);
        assertThat(products.getContent().get(0).getName()).isEqualTo("Product A");
    }

    @Test
    void testCreateProduct() {
        ProductRequestDTO requestDTO = new ProductRequestDTO();
        requestDTO.setName("Product A");
        requestDTO.setPrice(BigDecimal.valueOf(100));
        requestDTO.setDescription("Description A");

        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("Brand A");

        Author author = new Author();
        author.setId(1L);
        author.setName("Author A");

        Product product = new Product();
        product.setId(1L);
        product.setName("Product A");
        product.setPrice(BigDecimal.valueOf(100));
        product.setDescription("Description A");
        product.setBrand(brand);
        product.setAuthor(author);

        when(productRepository.save(any())).thenReturn(product);

        ProductResponseDTO responseDTO = productService.createProduct(requestDTO);

        assertThat(responseDTO.getName()).isEqualTo("Product A");
        assertThat(responseDTO.getPrice()).isEqualTo(BigDecimal.valueOf(100));
        assertThat(responseDTO.getBrandName()).isEqualTo("Brand A");
    }

    @Test
    void testGetProductById_NotFound() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> productService.getProductById(1L));
    }
}
