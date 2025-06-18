package br.com.pedrofurtadoflores.portfoliohexagonalcrud.domain.ports.out;

import java.util.List;
import java.util.Optional;

import br.com.pedrofurtadoflores.portfoliohexagonalcrud.domain.model.Product;

public interface ProductRepositoryPort {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);
}
