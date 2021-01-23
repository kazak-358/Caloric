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
import org.springframework.web.servlet.ModelAndView;

import net.caloric.model.Manufacturer;
import net.caloric.service.ManufacturerService;

@RestController
public class ManufacturerController {

	private final ManufacturerService manufacturerService;

	@Autowired
	public ManufacturerController(ManufacturerService manufacturerService) {
		this.manufacturerService = manufacturerService;
	}

	@PostMapping(value = "/manufacturers")
	public ResponseEntity<?> create(@RequestBody Manufacturer manufacturer) {
		manufacturerService.create(manufacturer);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping(value = "/manufacturers")
	public ModelAndView read() {
		final List<Manufacturer> manufacturers = manufacturerService.readAll();
		ModelAndView mav = new ModelAndView("manufacturers");
		mav.addObject("listManufacturer", manufacturers);
		return mav;
	}

	@GetMapping(value = "/manufacturers/{id}")
	public ResponseEntity<Manufacturer> read(@PathVariable(name = "id") int id) {
		final Manufacturer manufacturer = manufacturerService.read(id);

		return manufacturer != null
				? new ResponseEntity<>(manufacturer,
						HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping(value = "/manufacturers/{id}")
	public ResponseEntity<?> update(@PathVariable(name = "name") String name, @RequestBody Manufacturer manufacturer) {
		final boolean updated = manufacturerService.update(manufacturer, name);

		return updated
				? new ResponseEntity<>(HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	@DeleteMapping(value = "/manufacturers/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
		final boolean deleted = manufacturerService.delete(id);

		return deleted
				? new ResponseEntity<>(HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	@RequestMapping("/manufacturers/search")
	public ModelAndView search(@RequestParam String keyword) {
		List<Manufacturer> result = manufacturerService.search(keyword);
		ModelAndView mav = new ModelAndView("search_manufacturers");
		mav.addObject("result", result);

		return mav;
	}
}