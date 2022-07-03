package com.example.shoppinglist.service.impl;

import com.example.shoppinglist.model.entity.ProductEntity;
import com.example.shoppinglist.model.service.ProductServiceModel;
import com.example.shoppinglist.model.view.ProductViewModel;
import com.example.shoppinglist.repository.ProductRepository;
import com.example.shoppinglist.service.CategoryService;
import com.example.shoppinglist.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public boolean existsByName(String name) {
        return this.productRepository.existsByName(name);
    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel) {
        ProductEntity product = this.modelMapper.map(productServiceModel, ProductEntity.class);
        product.setCategory(this.categoryService.findCategoryByName(productServiceModel.getCategory()));
        this.productRepository.save(product);
    }

    @Override
    public List<ProductViewModel> getAllProducts() {
        return this.productRepository.findAll()
                .stream()
                .map(productEntity -> this.modelMapper.map(productEntity, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void buyAllProducts() {
        this.productRepository.deleteAll();
    }

    @Override
    public void buyProduct(Long id) {
        this.productRepository.deleteById(id);
    }
}
