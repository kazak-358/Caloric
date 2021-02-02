package net.caloric.service;

import java.util.List;

import net.caloric.dto.ProductDto;

public interface ProductService {

	public void create(ProductDto product);

	public List<ProductDto> readAll();

	public ProductDto read(Long id);

	public ProductDto update(Long id, ProductDto productDto);

	public boolean delete(Long id);

	public List<ProductDto> search(String keyword);
}