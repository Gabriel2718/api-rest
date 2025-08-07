package com.gabriel2718.api_rest.controllers;

import com.gabriel2718.api_rest.model.Product;
import com.gabriel2718.api_rest.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository repository;
    //Injeção de um objeto da interface ProductRepository

    @GetMapping
    public ResponseEntity getAll() {
        List<Product> listProducts = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(listProducts);
    }
    //Método executado quando a aplicação recebe uma requisição do tipo Get no endpoint '/products'
}
