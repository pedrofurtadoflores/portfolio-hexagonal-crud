package br.com.pedrofurtadoflores.portfoliohexagonalcrud.infrastructure.persistence.repository;

import br.com.pedrofurtadoflores.portfoliohexagonalcrud.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
}
