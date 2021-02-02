package net.caloric.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.caloric.dto.ProductDto;
import net.caloric.service.ProductService;

@RestController
public class ProductController {

	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping(value = "/products")
	public ResponseEntity<?> create(@RequestBody ProductDto product) {
		productService.create(product);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping(value = "/products")
	public ResponseEntity<List<ProductDto>> read() {
		final List<ProductDto> products = productService.readAll();

		return products != null && !products.isEmpty()
				? new ResponseEntity<>(products,
						HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/products/{id}")
	public ResponseEntity<ProductDto> read(@PathVariable(name = "id") Long id) {
		final ProductDto product = productService.read(id);

		return product != null
				? new ResponseEntity<>(
						product,
						HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping(value = "/products/{id}")
	public ResponseEntity<ProductDto> update(@PathVariable(name = "id") Long id, @RequestBody ProductDto product) {
		final ProductDto updated = productService.update(id, product);

		return updated != null ? new ResponseEntity<>(updated,
				HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	@DeleteMapping(value = "/products/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
		final boolean deleted = productService.delete(id);

		return deleted
				? new ResponseEntity<>(HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	@RequestMapping("/products/search")
	public ResponseEntity<List<ProductDto>> search(@RequestParam String keyword) {
		List<ProductDto> products = productService.search(keyword);
		return products != null && !products.isEmpty()
				? new ResponseEntity<>(products,
						HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}