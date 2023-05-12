package com.example.javacontrol.service;

import com.example.javacontrol.dto.CreateUpdateDTO;
import com.example.javacontrol.dto.ProductDTO;
import com.example.javacontrol.entity.Product;
import com.example.javacontrol.repository.ProductRepository;
import com.example.javacontrol.util.ModelConverterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelConverterUtil productMapper;


    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @Transactional
    public CreateUpdateDTO save(CreateUpdateDTO dto){
        var product = Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .build()
        ;

        productRepository.save(product);

        return dto;
    }

    public Product update(Long id, CreateUpdateDTO dto) {
        var product = productRepository.findById(id).orElseThrow();

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        productRepository.save(product);

        return product;
    }

    public void delete(Long id) {
        var product = productRepository.findById(id).orElseThrow();

        productRepository.delete(product);
    }
}
