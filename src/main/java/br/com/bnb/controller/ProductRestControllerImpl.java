package br.com.bnb.controller;

import br.com.bnb.dto.ProductRequestDTO;
import br.com.bnb.dto.ProductResponseDTO;
import br.com.bnb.repository.ProductSpecification;
import br.com.bnb.service.ProductService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
public class ProductRestControllerImpl implements ProductRestController {

        @Autowired
        private ProductService productService;

        @Override
        @Cacheable(value = "products", key = "T(org.springframework.cache.interceptor.SimpleKey).of(#name, #brandName, #authorName, #minPrice, #maxPrice, #pageable.pageNumber, #pageable.pageSize)")
        public Page<ProductResponseDTO> getAllProducts(
                String name,
                String brandName,
                String authorName,
                Double minPrice,
                Double maxPrice,
                Pageable pageable
        ) {
            ProductSpecification specification = new ProductSpecification();
            specification.setName(name);
            specification.setBrandName(brandName);
            specification.setAuthorName(authorName);
            specification.setMinPrice(minPrice);
            specification.setMaxPrice(maxPrice);
            return productService.getProducts(specification, pageable);
        }

        @Override
        @Cacheable(value = "productById", key = "#id")
        public ProductResponseDTO getProductById(Long id) {
            return productService.getProductById(id);
        }

        @Override
        public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
            return productService.createProduct(productRequestDTO);
        }

        @Override
        @CachePut(value = "productById", key = "#result.id")
        public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO) {
            return productService.updateProduct(id, productRequestDTO);
        }

        @Override
        @CachePut(value = "productById", key = "#id")
        public ProductResponseDTO partiallyUpdateProduct(Long id, ProductRequestDTO productRequestDTO) {
            return productService.partiallyUpdateProduct(id, productRequestDTO);
        }

        @Override
        @CacheEvict(value = "productById", key = "#id")
        public void deleteProduct(Long id) {
            productService.deleteProduct(id);
        }
    }
