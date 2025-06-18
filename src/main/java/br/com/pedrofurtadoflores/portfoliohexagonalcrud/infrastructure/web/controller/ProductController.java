package br.com.pedrofurtadoflores.portfoliohexagonalcrud.infrastructure.web.controller;

import br.com.pedrofurtadoflores.portfoliohexagonalcrud.domain.model.Product;
import br.com.pedrofurtadoflores.portfoliohexagonalcrud.domain.ports.in.ProductUseCase;
import br.com.pedrofurtadoflores.portfoliohexagonalcrud.infrastructure.web.dto.ProductRequestDTO;
import br.com.pedrofurtadoflores.portfoliohexagonalcrud.infrastructure.web.dto.ProductResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductUseCase productUseCase;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@Valid @RequestBody ProductRequestDTO dto) {
        Product product = toDomain(dto);
        Product saved = productUseCase.create(product);
        return ResponseEntity.created(URI.create("/api/products/" + saved.getId()))
                .body(toResponse(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable Long id) {
        Product found = productUseCase.getById(id);
        return ResponseEntity.ok(toResponse(found));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        List<ProductResponseDTO> list = productUseCase.getAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id,
                                                     @Valid @RequestBody ProductRequestDTO dto) {
        Product updated = productUseCase.update(id, toDomain(dto));
        return ResponseEntity.ok(toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    // === DTO Conversions ===

    private Product toDomain(ProductRequestDTO dto) {
        return Product.builder()
                .name(dto.getName())
                .category(dto.getCategory())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .build();
    }

    private ProductResponseDTO toResponse(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .price(product.getPrice())
                .stock(product.getStock())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
