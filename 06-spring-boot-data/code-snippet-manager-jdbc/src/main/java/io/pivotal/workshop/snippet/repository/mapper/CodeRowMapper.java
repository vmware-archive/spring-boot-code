package io.pivotal.workshop.snippet.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import io.pivotal.workshop.snippet.domain.Code;

public class CodeRowMapper implements RowMapper<Code> {

	@Override
	public Code mapRow(ResultSet rs, int rowNum) throws SQLException {
		Code code = new Code();
		code.setId(rs.getString("id"));
		code.setSource(rs.getString("source"));
		return code;
	}

}
