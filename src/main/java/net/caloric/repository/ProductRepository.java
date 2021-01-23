package net.caloric.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.caloric.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}