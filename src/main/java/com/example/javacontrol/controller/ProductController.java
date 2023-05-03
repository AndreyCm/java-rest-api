package com.example.javacontrol.controller;

import com.example.javacontrol.dto.ProductDTO;
import com.example.javacontrol.entity.Product;
import com.example.javacontrol.repository.ProductRepository;
import com.example.javacontrol.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {

    private final ModelMapper productMapper;

    private final ProductService productService;

    private final ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts() {
        var result = productService.findAll().stream().map(e -> productMapper.map(e, ProductDTO.class)).toList();

        return ResponseEntity.ok(result);
    }

    @PostConstruct
    private void init() {
        productRepository.save(new Product("Test", "Test description", 12.00f));
    }
}
