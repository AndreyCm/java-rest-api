package com.example.javacontrol.controller;

import com.example.javacontrol.dto.CreateUpdateDTO;
import com.example.javacontrol.dto.ProductDTO;
import com.example.javacontrol.entity.Product;
import com.example.javacontrol.repository.ProductRepository;
import com.example.javacontrol.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {

    private final ModelMapper productMapper;

    private final ProductService productService;

    private final ProductRepository productRepository;

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getProducts() {
        var result = productService.findAll().stream().map(e -> productMapper.map(e, ProductDTO.class)).toList();

        return ResponseEntity.ok(result);
    }

    @PostMapping("/")
    public ResponseEntity<CreateUpdateDTO> saveProduct(@Valid @RequestBody CreateUpdateDTO dto){
        productService.save(dto);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<Optional<Product>> getOne(@PathVariable Long id){
        var product = productRepository.findById(id);

        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody CreateUpdateDTO dto) {
        return ResponseEntity.ok(productService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.delete(id);

        return ResponseEntity.noContent().build();
    }

//    @PostConstruct
//    private void init() {
//        productRepository.save(new Product("Test", "Test description", 12.00f));
//        productRepository.save(new Product("Test2", "Test description 2", 22.00f));
//        productRepository.save(new Product("Test3", "Test description 3", 32.00f));
//        productRepository.save(new Product("Test4", "Test description 4", 42.00f));
//    }
}
