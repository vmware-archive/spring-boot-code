package io.pivotal.workshop.snippet.controller;

import io.pivotal.workshop.snippet.repository.LanguageRepository;
import io.pivotal.workshop.snippet.repository.SnippetRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SnippetController {

	
	private SnippetRepository snippetRepository;
	private LanguageRepository languageRepository;

	public SnippetController(SnippetRepository snippetRepository,LanguageRepository languageRepository){
		this.snippetRepository = snippetRepository;
		this.languageRepository = languageRepository;
	}
	
	@RequestMapping("/")
	public ModelAndView home(){

		assert snippetRepository != null;
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("langs", languageRepository.findAll());
		model.put("snippets", snippetRepository.findAll());
		
		return new ModelAndView("views/home",model);
	}

}