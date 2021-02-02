package net.caloric.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.caloric.dto.ProductDto;
import net.caloric.model.Manufacturer;
import net.caloric.model.Product;
import net.caloric.repository.ManufacturerRepository;
import net.caloric.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	@Override
	public void create(ProductDto productDto) {
		Product product = new Product();
		mapDtoToEntity(productDto, product);
		productRepository.save(product);
	}

	@Override
	public List<ProductDto> readAll() {
		List<ProductDto> productDtos = new ArrayList<>();
		productRepository.findAll().stream().sorted(Comparator.comparing(
				Product::getId)).forEach(product -> productDtos.add(mapEntityToDto(product)));
		return productDtos;
	}

	@Override
	public ProductDto read(Long id) {
		return mapEntityToDto(productRepository.getOne(id));
	}

	@Override
	public ProductDto update(Long id, ProductDto productDto) {
		Product product = productRepository.getOne(id);
		product.getManufacturers().clear();
		mapDtoToEntity(productDto, product);
		return mapEntityToDto(productRepository.save(product));
	}

	@Override
	public boolean delete(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			product.get().removeManufacturers();
			productRepository.deleteById(product.get().getId());
			return true;
		}
		return false;
	}

	@Override
	public List<ProductDto> search(String keyword) {
		List<ProductDto> productDtos = new ArrayList<>();
		productRepository.search(keyword).stream().forEach(product -> productDtos.add(mapEntityToDto(product)));
		return productDtos;
	}

	private void mapDtoToEntity(ProductDto productDto, Product product) {
		product.setName(productDto.getName());
		product.setCaloric(productDto.getCaloric());
		product.setCarbohydrates(productDto.getCarbohydrates());
		product.setFat(productDto.getFat());
		product.setProtein(productDto.getProtein());

		if (product.getManufacturers() == null) {
			product.setManufacturers(new HashSet<>());
		}
		productDto.getManufacturers().stream().forEach(manufacturerName -> {
			Manufacturer manufacturer = manufacturerRepository.findByName(manufacturerName);
			if (manufacturer == null) {
				manufacturer = new Manufacturer();
			}
			manufacturer.setName(manufacturerName);
			product.addManufacturer(manufacturer);
		});
	}

	private ProductDto mapEntityToDto(Product product) {
		if (product == null) {
			return null;
		}
		ProductDto productDto = new ProductDto();
		productDto.setName(product.getName());
		productDto.setId(product.getId());
		productDto.setCaloric(product.getCaloric());
		productDto.setCarbohydrates(product.getCarbohydrates());
		productDto.setFat(product.getFat());
		productDto.setProtein(product.getProtein());

		productDto
				.setManufacturers(product.getManufacturers().stream().map(Manufacturer::getName).collect(Collectors.toSet()));
		return productDto;
	}

}