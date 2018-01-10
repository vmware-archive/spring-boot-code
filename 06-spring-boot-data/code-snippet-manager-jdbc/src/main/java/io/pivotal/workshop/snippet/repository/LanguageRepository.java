package io.pivotal.workshop.snippet.repository;

import java.sql.PreparedStatement;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import io.pivotal.workshop.snippet.domain.Language;
import io.pivotal.workshop.snippet.repository.mapper.LanguageRowMapper;

@Repository
public class LanguageRepository implements SimpleRepository<Language>{

    private final String SQL_FIND_ALL = "select * from language";
    private final String SQL_FIND_ONE = "select * from language where name = ?";
    private final String SQL_INSERT = "insert into language(id,name,syntax) values(?,?,?)";
    private final String SQL_UPDATE = "update language set name = ?, syntax = ? where id = ?";
	
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public LanguageRepository(JdbcTemplate jdbcTemplate){
    		this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
	public Iterable<Language> findAll(){
		return this.jdbcTemplate.query(SQL_FIND_ALL, new LanguageRowMapper());
	}	
	
    @Override
	public void saveAll(Collection<Language> languages){
		languages.forEach( lang -> saveAll(lang) );
	}
	
    @Override
	public Language findById(String name) {
    		try {
    			return this.jdbcTemplate.queryForObject(SQL_FIND_ONE, new Object[]{name}, new LanguageRowMapper());
		} catch (EmptyResultDataAccessException ex){
    			return null;
    		}
	}

    @Override
	public Language saveAll(Language item) {
		assert item.getName() != null;
		
		Language language = this.findById(item.getName());
		
		if(language == null) {
			this.jdbcTemplate.update( psc -> {
				PreparedStatement ps = psc.prepareStatement(SQL_INSERT);
				ps.setString(1, item.getId());
				ps.setString(2, item.getName());
				ps.setString(3, item.getSyntax());
				return ps;
			});
			return item;
		}else {
			this.jdbcTemplate.update( psc -> {
				PreparedStatement ps = psc.prepareStatement(SQL_UPDATE);
				ps.setString(1, item.getName());
				ps.setString(2, item.getSyntax());
				ps.setString(3, item.getId());
				return ps;
			});
			return language;
		}
	}
}