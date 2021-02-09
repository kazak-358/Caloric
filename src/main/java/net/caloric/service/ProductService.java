package net.caloric.service;

import java.util.List;

import net.caloric.dto.ProductDto;

public interface ProductService {

	public List<ProductDto> readAll();

	public ProductDto read(Long pid, Long mid);

	public ProductDto save(ProductDto productDto);

	public boolean delete(Long pid);

	public boolean delete(Long pid, Long mid);

//	public List<ProductDto> search(String keyword);
}