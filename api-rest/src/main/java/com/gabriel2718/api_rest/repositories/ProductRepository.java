package com.gabriel2718.api_rest.repositories;

import com.gabriel2718.api_rest.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}

