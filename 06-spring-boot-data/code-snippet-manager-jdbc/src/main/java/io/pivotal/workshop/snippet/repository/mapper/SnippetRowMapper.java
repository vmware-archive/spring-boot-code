package io.pivotal.workshop.snippet.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import io.pivotal.workshop.snippet.domain.Code;
import io.pivotal.workshop.snippet.domain.Language;
import io.pivotal.workshop.snippet.domain.Snippet;

public class SnippetRowMapper implements RowMapper<Snippet> {

	@Override
	public Snippet mapRow(ResultSet rs, int rowNum) throws SQLException {
		Language lang = new Language();
		lang.setId(rs.getString("lang_id"));
		lang.setName(rs.getString("lang_name"));
		lang.setSyntax(rs.getString("lang_syntax"));

		Code code = new Code();
		code.setId(rs.getString("code_id"));
		code.setSource(rs.getString("code_source"));

		Snippet snippet = new Snippet();
		snippet.setId(rs.getString("id"));
		snippet.setTitle(rs.getString("title"));
		snippet.setDescription(rs.getString("description"));
		snippet.setKeywords(rs.getString("keywords"));
		snippet.setCreated(rs.getDate("created"));
		snippet.setModified(rs.getDate("modified"));
		snippet.setLang(lang);
		snippet.setCode(code);
		
		return snippet;
	}

}
