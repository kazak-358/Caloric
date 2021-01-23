package net.caloric.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.caloric.model.Product;
import net.caloric.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void create(Product product) {
		productRepository.save(product);
	}

	@Override
	public List<Product> readAll() {
		return productRepository.findAll().stream().sorted(Comparator.comparing(
				Product::getId))
				.collect(Collectors.toList());
	}

	@Override
	public Product read(long id) {
		return productRepository.getOne(id);
	}

	@Override
	public boolean update(Product product, String name) {
		if (productRepository.existsById(product.getId())) {
			product.setName(name);
			productRepository.save(product);
			return true;
		}

		return false;
	}

	@Override
	public boolean delete(long id) {
		if (productRepository.existsById(id)) {
			productRepository.deleteById(id);
			return true;
		}
		return false;
	}
}