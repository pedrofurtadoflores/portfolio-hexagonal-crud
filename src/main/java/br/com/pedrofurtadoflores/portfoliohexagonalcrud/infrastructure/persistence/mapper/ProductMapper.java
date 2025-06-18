package br.com.pedrofurtadoflores.portfoliohexagonalcrud.infrastructure.persistence.mapper;

import br.com.pedrofurtadoflores.portfoliohexagonalcrud.domain.model.Product;
import br.com.pedrofurtadoflores.portfoliohexagonalcrud.infrastructure.persistence.entity.ProductEntity;

public class ProductMapper {

    public static ProductEntity toEntity(Product product) {
        if (product == null) return null;

        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .price(product.getPrice())
                .stock(product.getStock())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public static Product toDomain(ProductEntity entity) {
        if (entity == null) return null;

        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getCategory(),
                entity.getPrice(),
                entity.getStock(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
