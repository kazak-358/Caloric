package net.caloric.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public ModelAndView save(@ModelAttribute("manufacturer") Manufacturer manufacturer) {
		ModelAndView mav = null;
		try {
			manufacturerService.save(manufacturer);
			mav = new ModelAndView("saved_manufacturer");
		} catch (Exception e) {
			mav = new ModelAndView("error");
		}
		return mav;
	}

	@GetMapping(value = "/manufacturers")
	public ModelAndView read() {
		final List<Manufacturer> manufacturers = manufacturerService.readAll();
		ModelAndView mav = new ModelAndView("manufacturers");
		mav.addObject("listManufacturer", manufacturers);
		return mav;
	}

	@RequestMapping("/manufacturers/search")
	public ModelAndView search(@RequestParam String keyword) {
		List<Manufacturer> result = manufacturerService.search(keyword);
		ModelAndView mav = new ModelAndView("search_manufacturers");
		mav.addObject("result", result);

		return mav;
	}

	@RequestMapping(value = "/manufacturers/delete")
	public ResponseEntity<?> delete(@RequestParam long id) {
		return manufacturerService.delete(id) ? ControllerConstants.OK_RESPONSE : ControllerConstants.NOT_MODIFIED_RESPONSE;
	}

	@PostMapping(value = "/manufacturers")
	public ResponseEntity<?> jsonCreate(@RequestBody Manufacturer manufacturer) {
		try {
			manufacturerService.save(manufacturer);
			return ControllerConstants.CREATED_RESPONSE;
		} catch (Exception e) {
			return ControllerConstants.NOT_MODIFIED_RESPONSE;
		}
	}

	@PutMapping(value = "/manufacturers")
	public ResponseEntity<?> jsonUpdate(@RequestBody Manufacturer manufacturer) {
		try {
			manufacturerService.save(manufacturer);
			return ControllerConstants.OK_RESPONSE;
		} catch (Exception e) {
			return ControllerConstants.NOT_MODIFIED_RESPONSE;
		}
	}

	@DeleteMapping(value = "/manufacturers")
	public ResponseEntity<?> jsonDelete(@RequestBody Manufacturer manufacturer) {
		try {
			return manufacturerService.delete(manufacturer.getId()) ? ControllerConstants.OK_RESPONSE
					: ControllerConstants.NOT_MODIFIED_RESPONSE;
		} catch (Exception e) {
			return ControllerConstants.NOT_MODIFIED_RESPONSE;
		}
	}

	@GetMapping("/manufacturers/json_search")
	public ResponseEntity<List<Manufacturer>> jsonSearch(@RequestParam String keyword) {
		List<Manufacturer> result = manufacturerService.search(keyword);
		return result != null && !result.isEmpty() ? new ResponseEntity<>(result, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
