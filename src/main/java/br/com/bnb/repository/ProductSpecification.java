package br.com.bnb.repository;

import br.com.bnb.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductSpecification implements Specification<Product> {

    private String name;
    private String brandName;
    private String authorName;
    private Double minPrice;
    private Double maxPrice;

    // Getters e setters para os parâmetros de filtro

    public void setName(String name) {
        this.name = name;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public Predicate toPredicate(
            jakarta.persistence.criteria.Root<Product> root,
            jakarta.persistence.criteria.CriteriaQuery<?> query,
            jakarta.persistence.criteria.CriteriaBuilder criteriaBuilder
    ) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(name)) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }

        if (StringUtils.hasText(brandName)) {
            Join<Object, Object> brandJoin = root.join("brand");
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(brandJoin.get("name")), "%" + brandName.toLowerCase() + "%"));
        }

        if (StringUtils.hasText(authorName)) {
            Join<Object, Object> authorJoin = root.join("author");
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(authorJoin.get("name")), "%" + authorName.toLowerCase() + "%"));
        }

        if (minPrice != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
        }

        if (maxPrice != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
