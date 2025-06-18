package br.com.pedrofurtadoflores.portfoliohexagonalcrud.infrastructure.persistence.repository;

import br.com.pedrofurtadoflores.portfoliohexagonalcrud.domain.model.Product;
import br.com.pedrofurtadoflores.portfoliohexagonalcrud.domain.ports.out.ProductRepositoryPort;
import br.com.pedrofurtadoflores.portfoliohexagonalcrud.infrastructure.persistence.entity.ProductEntity;
import br.com.pedrofurtadoflores.portfoliohexagonalcrud.infrastructure.persistence.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository jpaRepository;

    @Override
    public Product save(Product product) {
        ProductEntity entity = ProductMapper.toEntity(product);
        ProductEntity saved = jpaRepository.save(entity);
        return ProductMapper.toDomain(saved);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaRepository.findById(id)
                .map(ProductMapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(ProductMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
