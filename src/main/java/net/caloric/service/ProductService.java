package net.caloric.service;

import java.util.List;

import net.caloric.model.Product;

public interface ProductService {

	void create(Product product);

	List<Product> readAll();

	Product read(long id);

	boolean update(Product product, String name);

	boolean delete(long id);
}