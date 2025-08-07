package com.gabriel2718.api_rest.controllers;

import com.gabriel2718.api_rest.dtos.ProductDto;
import com.gabriel2718.api_rest.model.Product;
import com.gabriel2718.api_rest.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable(value = "id") Integer id) { //O parâmetro id é um caminho variável que se refere ao id do corpo da requisição
        Optional product = repository.findById(id);
        if(product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(product.get());
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ProductDto dto) {
        Product p = new Product();
        BeanUtils.copyProperties(dto, p);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(p));
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity deleteById(@PathVariable(value = "id") Integer id) {
        Optional<Product> product = repository.findById(id);
        if(product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        repository.delete(product.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@PathVariable(value = "id") Integer id, @RequestBody ProductDto dto) {
        Optional<Product> product = repository.findById(id);
        if(product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        Product productModel = product.get();
        BeanUtils.copyProperties(dto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(productModel));
    }
}
