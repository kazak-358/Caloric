package net.caloric.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	public ModelAndView read() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

}
