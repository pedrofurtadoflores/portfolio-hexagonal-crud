package br.com.pedrofurtadoflores.portfoliohexagonalcrud.domain.ports.in;

import br.com.pedrofurtadoflores.portfoliohexagonalcrud.domain.model.Product;

import java.util.List;

public interface ProductUseCase {

    Product create(Product product);
    Product update(Long id, Product product);
    void delete(Long id);
    Product getById(Long id);
    List<Product> getAll();
}