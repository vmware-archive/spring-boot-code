package io.pivotal.workshop.snippet.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.pivotal.workshop.snippet.domain.Snippet;
import io.pivotal.workshop.snippet.repository.LanguageRepository;
import io.pivotal.workshop.snippet.repository.SnippetRepository;

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
	
	@RequestMapping("/snippets")
	public ResponseEntity<?> snippets(){
		assert snippetRepository != null;
		return ResponseEntity.ok(snippetRepository.findAll());
	}
	
	@RequestMapping("/snippets/{id}")
	public ResponseEntity<?> snippet(@PathVariable("id") String id){
		assert snippetRepository != null;
		return ResponseEntity.ok(snippetRepository.findById(id));
	}
	
	@RequestMapping(value="/snippets",method = { RequestMethod.POST, RequestMethod.PUT})
	public  ResponseEntity<?> upsert(@RequestBody Snippet snippet){
		assert snippetRepository != null;
		
		Snippet _snippet = snippetRepository.save(snippet);
		assert _snippet != null;
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/" + _snippet.getId())
				.buildAndExpand().toUri());
		
		return new ResponseEntity<>(_snippet,httpHeaders,HttpStatus.CREATED);
	}

}