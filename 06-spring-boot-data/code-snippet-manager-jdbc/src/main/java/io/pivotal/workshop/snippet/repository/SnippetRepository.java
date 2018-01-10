package io.pivotal.workshop.snippet.repository;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import io.pivotal.workshop.snippet.domain.CrossSnippetLanguageCode;
import io.pivotal.workshop.snippet.domain.Snippet;
import io.pivotal.workshop.snippet.repository.mapper.SnippetRowMapper;

@Repository
public class SnippetRepository implements SimpleRepository<Snippet> {

	private final String SQL_FIND_ALL = "select s.*,l.id lang_id, l.name lang_name, l.syntax lang_syntax, c.id code_id, c.source code_source from cross_snippet_language_code cx " +
			"inner join snippet s on cx.snippet_id = s.id " +
			"inner join language l on cx.language_id = l.id " +
			"inner join code c on cx.code_id = c.id ";
	
	private final String SQL_FIND_ONE = SQL_FIND_ALL + " where s.id = ?";
	private final String SQL_INSERT = "insert into snippet(id,title, keywords, description, created, modified) values(?,?,?,?,?,?)";
	private final String SQL_UPDATE = "update snippet set title = ?, keywords = ?, description = ?, modified = ? where id = ?";

	private JdbcTemplate jdbcTemplate;
	private LanguageRepository langRepo;
	private CodeRepository codeRepo;
	private CrossSnippetLanguageCodeRepository crossRepo;

	@Autowired
	public SnippetRepository(JdbcTemplate jdbcTemplate, LanguageRepository langRepo, CodeRepository codeRepo, CrossSnippetLanguageCodeRepository crossRepo) {
		this.jdbcTemplate = jdbcTemplate;
		this.langRepo = langRepo;
		this.codeRepo = codeRepo;
		this.crossRepo = crossRepo;
	}

	@Override
	public List<Snippet> findAll() {
		return this.jdbcTemplate.query(SQL_FIND_ALL, new SnippetRowMapper());
	}

	@Override
	public void saveAll(Collection<Snippet> items) {
		items.forEach(code -> this.saveAll(code));
	}

	@Override
	public Snippet findById(String id) {
		try {
			return this.jdbcTemplate.queryForObject(SQL_FIND_ONE, new Object[] { id }, new SnippetRowMapper());
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public Snippet saveAll(final Snippet item) {
		assert item.getId() != null;
		assert item.getTitle() != null;
		assert item.getLang() != null;
		assert item.getCode() != null;

		Snippet snippet = this.findById(item.getId());

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		if (snippet == null) {
			
			this.crossRepo.saveAll(new CrossSnippetLanguageCode(item.getId(), item.getLang().getId(), item.getCode().getId()));
			this.langRepo.saveAll(item.getLang());
			this.codeRepo.saveAll(item.getCode());
			
			this.jdbcTemplate.update(psc -> {
				PreparedStatement ps = psc.prepareStatement(SQL_INSERT);
				ps.setString(1, item.getId());
				ps.setString(2, item.getTitle());
				ps.setString(3, item.getKeywords());
				ps.setString(4, item.getDescription());
				ps.setString(5, dateFormat.format(date));
				ps.setString(6, dateFormat.format(date));
				return ps;
			});
			
			return item;
			
		} else {
			this.crossRepo.saveAll(new CrossSnippetLanguageCode(snippet.getId(), snippet.getLang().getId(), snippet.getCode().getId()));
			this.langRepo.saveAll(item.getLang());
			this.codeRepo.saveAll(item.getCode());
			
			this.jdbcTemplate.update(psc -> {
				PreparedStatement ps = psc.prepareStatement(SQL_UPDATE);
				ps.setString(1, item.getTitle());
				ps.setString(2, item.getKeywords());
				ps.setString(3, item.getDescription());
				ps.setString(4, dateFormat.format(date));
				ps.setString(5, item.getId());
				return ps;
			});
			
			return snippet;
		}
	}
	

}
