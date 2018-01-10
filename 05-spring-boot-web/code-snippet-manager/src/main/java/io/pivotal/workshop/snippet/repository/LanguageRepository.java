package io.pivotal.workshop.snippet.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Repository;

import io.pivotal.workshop.snippet.domain.Language;

@Repository
public class LanguageRepository implements SimpleRepository<Language>{

    private List<Language> languages = new ArrayList<>();
	
	public Iterable<Language> findAll(){
		return languages;
	}	
	
	public void saveAll(Collection<Language> languages){
		this.languages.addAll(languages);
	}
	
	public Language findById(String name) {
		Optional<Language> language = StreamSupport
			.stream(this.languages.spliterator(), false)
			.filter(lang -> lang.getName().equals(name))
			.findFirst();
		
		if (language.isPresent()) return language.get();
		
		return null;
	}

	public Language saveAll(Language item) {
		assert item.getName() != null;
		
		Language language = this.findById(item.getName());
		
		if(language == null) {
			this.languages.add(item);
			return item;
		}else {
			language.setName(item.getName());
			language.setSyntax(item.getSyntax());
			return language;
		}
	}
}