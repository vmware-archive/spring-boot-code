package io.pivotal.workshop.snippet.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import io.pivotal.workshop.snippet.domain.CrossSnippetLanguageCode;

@Repository
public class CrossSnippetLanguageCodeRepository implements SimpleRepository<CrossSnippetLanguageCode> {

	private final String SQL_FIND_ALL = "select * from cross_snippet_language_code";
	private final String SQL_FIND_ONE = "select * from cross_snippet_language_code where snippet_id = ?";
	private final String SQL_INSERT = "insert into cross_snippet_language_code(snippet_id,language_id,code_id) values(?,?,?)";
	private final String SQL_UPDATE = "update cross_snippet_language_code set langauge_id = ?, code_id = ? where snippet_id = ?";
	private JdbcTemplate jdbcTemplate;

	private final RowMapper<CrossSnippetLanguageCode> rowMapper = (ResultSet rs, int row) -> {
		CrossSnippetLanguageCode cross = new CrossSnippetLanguageCode();
		cross.setSnippetId(rs.getString("snippet_id"));
		cross.setLanguageId(rs.getString("language_id"));
		cross.setCodeId(rs.getString("code_id"));
		return cross;
	};

	@Autowired
	public CrossSnippetLanguageCodeRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Iterable<CrossSnippetLanguageCode> findAll() {
		return this.jdbcTemplate.query(SQL_FIND_ALL, rowMapper);
	}

	@Override
	public void saveAll(Collection<CrossSnippetLanguageCode> items) {
		items.forEach(cross -> {
			this.saveAll(cross);
		});
	}

	@Override
	public CrossSnippetLanguageCode saveAll(CrossSnippetLanguageCode item) {
		assert item.getSnippetId() != null;
		assert item.getLanguageId() != null;
		assert item.getCodeId() != null;

		CrossSnippetLanguageCode cross = this.findById(item.getSnippetId());

		if (cross == null) {
			this.jdbcTemplate.update(psc -> {
				PreparedStatement ps = psc.prepareStatement(SQL_INSERT);
				ps.setString(1, item.getSnippetId());
				ps.setString(2, item.getLanguageId());
				ps.setString(3, item.getCodeId());
				return ps;
			});
			return item;
		} else {
			this.jdbcTemplate.update(psc -> {
				PreparedStatement ps = psc.prepareStatement(SQL_UPDATE);
				ps.setString(1, item.getLanguageId());
				ps.setString(2, item.getCodeId());
				ps.setString(3, item.getSnippetId());
				return ps;
			});
			return cross;
		}

	}

	@Override
	public CrossSnippetLanguageCode findById(String id) {
		try {
			return this.jdbcTemplate.queryForObject(SQL_FIND_ONE, new Object[] { id }, rowMapper);
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

}
