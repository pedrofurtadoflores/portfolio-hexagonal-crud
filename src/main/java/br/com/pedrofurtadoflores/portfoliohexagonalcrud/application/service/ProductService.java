package br.com.pedrofurtadoflores.portfoliohexagonalcrud.application.service;

import br.com.pedrofurtadoflores.portfoliohexagonalcrud.domain.model.Product;
import br.com.pedrofurtadoflores.portfoliohexagonalcrud.domain.ports.in.ProductUseCase;
import br.com.pedrofurtadoflores.portfoliohexagonalcrud.domain.ports.out.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductUseCase {

    private final ProductRepositoryPort repository;

    @Override
    public Product create(Product product) {
        return repository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existing.setName(product.getName());
        existing.setCategory(product.getCategory());
        existing.setPrice(product.getPrice());
        existing.setStock(product.getStock());
        existing.setUpdatedAt(product.getUpdatedAt());

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }
}
