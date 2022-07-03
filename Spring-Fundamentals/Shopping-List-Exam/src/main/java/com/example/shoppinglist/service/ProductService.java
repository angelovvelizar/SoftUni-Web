package com.example.shoppinglist.service;

import com.example.shoppinglist.model.service.ProductServiceModel;
import com.example.shoppinglist.model.view.ProductViewModel;

import java.util.List;

public interface ProductService {

    boolean existsByName(String name);

    void addProduct(ProductServiceModel productServiceModel);

    List<ProductViewModel> getAllProducts();

    void buyAllProducts();

    void buyProduct(Long id);
}
