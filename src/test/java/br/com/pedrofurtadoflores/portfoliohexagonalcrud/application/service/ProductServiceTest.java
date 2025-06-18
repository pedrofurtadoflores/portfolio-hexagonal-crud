package br.com.pedrofurtadoflores.portfoliohexagonalcrud.application.service;

import br.com.pedrofurtadoflores.portfoliohexagonalcrud.domain.model.Product;
import br.com.pedrofurtadoflores.portfoliohexagonalcrud.domain.ports.out.ProductRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepositoryPort repository;

    @InjectMocks
    private ProductService service;

    private AutoCloseable mocks;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);

        sampleProduct = Product.builder()
                .id(1L)
                .name("Mouse Gamer")
                .category("Periféricos")
                .price(new BigDecimal("149.90"))
                .stock(10)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    void shouldCreateProduct() {
        when(repository.save(any())).thenReturn(sampleProduct);

        Product created = service.create(sampleProduct);

        assertNotNull(created);
        assertEquals("Mouse Gamer", created.getName());
        verify(repository).save(sampleProduct);
    }

    @Test
    void shouldReturnProductById() {
        when(repository.findById(1L)).thenReturn(Optional.of(sampleProduct));

        Product found = service.getById(1L);

        assertEquals(1L, found.getId());
        assertEquals("Mouse Gamer", found.getName());
        verify(repository).findById(1L);
    }

    @Test
    void shouldThrowWhenProductNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.getById(99L));
        assertTrue(exception.getMessage().contains("Product not found"));
    }

    @Test
    void shouldReturnAllProducts() {
        when(repository.findAll()).thenReturn(List.of(sampleProduct));

        List<Product> all = service.getAll();

        assertEquals(1, all.size());
        verify(repository).findAll();
    }

    @Test
    void shouldDeleteProduct() {
        doNothing().when(repository).deleteById(1L);

        service.delete(1L);

        verify(repository).deleteById(1L);
    }
}
