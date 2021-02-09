package net.caloric.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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
	public List<ProductDto> readAll() {
		List<ProductDto> productDtos = new ArrayList<>();
		productRepository.findAll().stream().sorted(Comparator.comparing(
				Product::getId)).forEach(product -> productDtos.addAll(mapEntityToDtos(product)));
		return productDtos;
	}

	@Override
	public ProductDto read(Long pid, Long mid) {
		return mapEntityToDtos(productRepository.getOne(pid)).stream().filter(product -> product.getManufacturerId()
				.equals(mid))
				.findFirst().orElse(null);

	}

	@Override
	public ProductDto save(ProductDto productDto) {
		Product product = null;
		if (productDto.getId() == null) {
			product = new Product();
		} else {
			// TODO обработать если удален
			product = productRepository.findById(productDto.getId()).orElse(new Product());
		}
		mapDtoToEntity(productDto, product);
		product = productRepository.save(product);
		return productDto; // TODO по хорошему надо вытащить новый productDto из product
	}

	@Override
	public boolean delete(Long pid, Long mid) {
		// удаляем связь продукт-производитель
		Product product = productRepository.findById(pid).orElse(null);
		if (product != null){
			Manufacturer manufacturer = product.getManufacturers().stream().filter(item -> item.getId().equals(mid)).findFirst()
					.orElse(null);
			if (manufacturer != null) {
				product.removeManufacturer(manufacturer);
				productRepository.save(product);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean delete(Long pid) {
		// удаляем продукт целиком
		Product product = productRepository.findById(pid).orElse(null);
		if (product != null) {
			product.removeManufacturers();
			productRepository.delete(product);
			return true;
		}
		return false;
	}

//	@Override
//	public List<ProductDto> search(String keyword) {
//		List<ProductDto> productDtos = new ArrayList<>();
//		productRepository.search(keyword).stream().forEach(product -> productDtos.add(mapEntityToDto(product)));
//		return productDtos;
//	}

	private void mapDtoToEntity(ProductDto productDto, Product product) {
		product.setName(productDto.getName());
		product.setCaloric(productDto.getCaloric());
		product.setCarbohydrates(productDto.getCarbohydrates());
		product.setFat(productDto.getFat());
		product.setProtein(productDto.getProtein());

		if (product.getManufacturers() == null) {
			product.setManufacturers(new HashSet<>());
		}
		// есть ли новый производитель в коллекции
		Optional<Manufacturer> optNewManufacturer = product.getManufacturers()
				.stream()
				.filter(man -> man.getName().equals(productDto.getManufacturerName())).findFirst();

		Manufacturer oldManufacturer = product.getManufacturers().stream()
				.filter(man -> man.getId().equals(productDto.getManufacturerId())).findFirst().orElse(null);

		// выбрали нового производителя
		if (!optNewManufacturer.isPresent()) {
			Manufacturer newManufacturer = manufacturerRepository.findByName(productDto.getManufacturerName());
			product.addManufacturer(newManufacturer);
			if (oldManufacturer != null) {
				product.removeManufacturer(oldManufacturer);
			}
		} else if (!optNewManufacturer.get().getId().equals(productDto.getManufacturerId())) {
			// поменяли производителя на того который уже есть в списке => удалить старого
			product.removeManufacturer(oldManufacturer);
		}
	}

	private List<ProductDto> mapEntityToDtos(Product product) {
		if (product == null || product.getManufacturers().isEmpty()) {
			return Collections.emptyList();
		}
		List<ProductDto> productDtos = new ArrayList<>();
		product.getManufacturers().forEach(manufacturer -> {
			ProductDto productDto = new ProductDto();
			productDto.setName(product.getName());
			productDto.setId(product.getId());
			productDto.setCaloric(product.getCaloric());
			productDto.setCarbohydrates(product.getCarbohydrates());
			productDto.setFat(product.getFat());
			productDto.setProtein(product.getProtein());
			productDto.setManufacturerId(manufacturer.getId());
			productDto.setManufacturerName(manufacturer.getName());
			productDtos.add(productDto);
		});
		return productDtos;
	}

}