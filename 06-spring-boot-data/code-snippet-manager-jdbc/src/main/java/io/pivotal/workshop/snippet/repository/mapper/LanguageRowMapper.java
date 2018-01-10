package io.pivotal.workshop.snippet.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import io.pivotal.workshop.snippet.domain.Language;

public class LanguageRowMapper implements RowMapper<Language> {

	@Override
	public Language mapRow(ResultSet rs, int rowNum) throws SQLException {
		 Language lang = new Language();
		 lang.setId(rs.getString("id"));
		 lang.setName(rs.getString("name"));
		 lang.setSyntax(rs.getString("syntax"));
		return lang;
	}

}
