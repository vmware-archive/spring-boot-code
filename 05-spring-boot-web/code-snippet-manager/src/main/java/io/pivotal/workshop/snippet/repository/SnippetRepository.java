package io.pivotal.workshop.snippet.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import io.pivotal.workshop.snippet.domain.Snippet;

@Repository
public class SnippetRepository implements SimpleRepository<Snippet>{
	
	private List<Snippet> snippets = new ArrayList<>();
	
	@Override
	public Snippet saveAll(Snippet snippet){
		assert snippet.getTitle() != null;
		assert snippet.getCode() != null;
		assert snippet.getLang() != null;
		
		Snippet _snippet =  null;
		
		if (snippet.getId() == null) {
			_snippet = new Snippet(snippet.getTitle(), snippet.getLang(),snippet.getCode());
			
		} else {
			 _snippet = this.findById(snippet.getId());
			 if(_snippet != null){
				 _snippet.setTitle(snippet.getTitle());
				 _snippet.setCode(snippet.getCode());
				 _snippet.setLang(snippet.getLang());
				 _snippet.setModified(new Date());
			 }
		}
		
		return _snippet;
	}
	
	@Override
	public Iterable<Snippet> findAll(){
		return this.snippets;
	}
	
	@Override
	public Snippet findById(String id){
		Optional<Snippet> result = snippets.stream()
				.filter(snippet -> snippet.getId().equals(id))
				.findFirst();
		
		if (result.isPresent()) return result.get();
		
		return null;
	}

	@Override
	public void saveAll(Collection<Snippet> items) {
		this.snippets.addAll(items);
	}
}
