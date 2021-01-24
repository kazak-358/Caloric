package net.caloric.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

	@RequestMapping("/manufacturers/new")
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView("new_manufacturer");
		Manufacturer manufacturer = new Manufacturer();
		mav.addObject("manufacturer", manufacturer);

		return mav;
	}

	@RequestMapping("/manufacturers/edit")
	public ModelAndView edit(@RequestParam long id) {
		ModelAndView mav = new ModelAndView("edit_manufacturer");
		Manufacturer manufacturer = manufacturerService.read(id);
		mav.addObject("manufacturer", manufacturer);

		return mav;
	}

	@PostMapping(value = "/manufacturers/save")
	public ResponseEntity<?> save(@ModelAttribute("manufacturer") Manufacturer manufacturer) {
		manufacturerService.save(manufacturer);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping(value = "/manufacturers")
	public ModelAndView read() {
		final List<Manufacturer> manufacturers = manufacturerService.readAll();
		ModelAndView mav = new ModelAndView("manufacturers");
		mav.addObject("listManufacturer", manufacturers);
		return mav;
	}

	@RequestMapping(value = "/manufacturers/delete")
	public ResponseEntity<?> delete(@RequestParam long id) {
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
