package io.pivotal.workshop.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SimpleController{

	Logger log = LoggerFactory.getLogger(SimpleController.class);

	@RequestMapping("/")
	public ModelAndView index(){
		log.debug("About to redirect...");
		return new ModelAndView("redirect:/showMessage");
	}

	@RequestMapping(value="/showMessage",method = RequestMethod.GET)
	public ModelAndView helloWorld(){
		final String message = "Simple Spring MVC Web App with Thymeleaf and Spring 5";
 		log.debug("Showing the Message: " + message);
		ModelAndView model = new ModelAndView("showMessage");
		model.addObject("message", message);
		return model;
	}
}