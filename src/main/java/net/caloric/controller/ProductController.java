package net.caloric.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import net.caloric.dto.ProductDto;
import net.caloric.model.Manufacturer;
import net.caloric.service.ManufacturerService;
import net.caloric.service.ProductService;

@RestController
public class ProductController {

	private final ProductService productService;
	private final ManufacturerService manufacturerService;

	private HashMap<Long, String> manufacturerCache;

	@Autowired
	public ProductController(ProductService productService, ManufacturerService manufacturerService) {
		this.productService = productService;
		this.manufacturerService = manufacturerService;
	}

	@ModelAttribute("manufacturerCache")
	public List<Manufacturer> getManufacturer() {
		return manufacturerService.readAll();
	}

	@RequestMapping("/products/new")
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView("new_product");
		ProductDto product = new ProductDto();
		List<Manufacturer> manufacturers = manufacturerService.readAll();
		manufacturerCache = new HashMap<>();
		for (Manufacturer mnufacturer : manufacturers) {
			manufacturerCache.put(mnufacturer.getId(), mnufacturer.getName());
		}
		mav.addObject("product", product);

		return mav;
	}

	@RequestMapping("/products/edit")
	public ModelAndView edit(@RequestParam Long pid, @RequestParam Long mid) {
		ModelAndView mav = new ModelAndView("edit_product");
		ProductDto product = productService.read(pid, mid);

		List<Manufacturer> manufacturers = manufacturerService.readAll();
		manufacturerCache = new HashMap<>();
		for (Manufacturer mnufacturer : manufacturers) {
			manufacturerCache.put(mnufacturer.getId(), mnufacturer.getName());
		}
		mav.addObject("product", product);

		return mav;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.registerCustomEditor(Set.class, "manufacturers", new CustomCollectionEditor(Set.class) {
			@Override
			protected Object convertElement(Object element) {
				if (element instanceof String) {
					return manufacturerCache.get(Long.parseLong(element.toString()));
				}
				return null;
			}
		});
	}

//	@PostMapping(value = "/products")
//	public ResponseEntity<?> create(@RequestBody ProductDto product) {
//		productService.create(product);
//		return new ResponseEntity<>(HttpStatus.CREATED);
//	}

	@GetMapping(value = "/products")
	public ModelAndView read() {
		final List<ProductDto> products = productService.readAll();
		ModelAndView mav = new ModelAndView("products");
		mav.addObject("listProduct", products);
		return mav;
	}

//	@GetMapping(value = "/products")
//	public ResponseEntity<List<ProductDto>> readJson() {
//		final List<ProductDto> products = productService.readAll();
//
//		return products != null && !products.isEmpty()? new ResponseEntity<>(products,HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	}

	@PostMapping(value = "/products/save")
	public ModelAndView save(@ModelAttribute("product") ProductDto productDto) {
		ModelAndView mav = null;
		try {
			productService.save(productDto);
			mav = new ModelAndView("saved_product");
		} catch (Exception e) {
			mav = new ModelAndView("error");
		}
		return mav;
	}

	@RequestMapping(value = "/products/delete")
	public ResponseEntity<?> delete(@RequestParam Long pid, @RequestParam Long mid) {
		return productService.delete(pid, mid) ? ControllerConstants.OK_RESPONSE : ControllerConstants.NOT_MODIFIED_RESPONSE;
	}

//	@RequestMapping(value = "/products/delete")
//	public ResponseEntity<?> delete(@RequestParam Long pid) {
//		return productService.delete(pid) ? ControllerConstants.OK_RESPONSE : ControllerConstants.NOT_MODIFIED_RESPONSE;
//	}

//	@DeleteMapping(value = "/products/{id}")
//	public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
//		final boolean deleted = productService.delete(id);
//
//		return deleted
//				? new ResponseEntity<>(HttpStatus.OK)
//				: new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//	}
//
//	@RequestMapping("/products/search")
//	public ResponseEntity<List<ProductDto>> search(@RequestParam String keyword) {
//		List<ProductDto> products = productService.search(keyword);
//		return products != null && !products.isEmpty()
//				? new ResponseEntity<>(products,
//						HttpStatus.OK)
//				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	}

}