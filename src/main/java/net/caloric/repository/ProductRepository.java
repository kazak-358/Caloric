package net.caloric.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.caloric.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query(value = "SELECT c FROM Product c WHERE lower(c.name) LIKE '%' || lower(:keyword) || '%'")
	public List<Product> search(@Param("keyword") String keyword);
}