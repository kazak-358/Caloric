package net.caloric.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@GetMapping(value = "/")
	public ModelAndView read() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

}
