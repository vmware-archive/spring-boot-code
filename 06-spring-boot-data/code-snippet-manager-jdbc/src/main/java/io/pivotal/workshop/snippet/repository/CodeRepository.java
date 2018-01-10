package io.pivotal.workshop.snippet.repository;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import io.pivotal.workshop.snippet.domain.Code;
import io.pivotal.workshop.snippet.repository.mapper.CodeRowMapper;

@Repository
public class CodeRepository implements SimpleRepository<Code> {

	private final String SQL_FIND_ALL = "select * from code";
	private final String SQL_FIND_ONE = "select * from code where id = ?";
	private final String SQL_INSERT = "insert into code(id,source) values(?,?)";
	private final String SQL_UPDATE = "update code set source = ? where id = ?";

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public CodeRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Code> findAll() {
		return this.jdbcTemplate.query(SQL_FIND_ALL, new CodeRowMapper());
	}

	@Override
	public void saveAll(Collection<Code> items) {
		items.forEach(code -> this.saveAll(code));
	}

	@Override
	public Code findById(String id) {
		try {
			return this.jdbcTemplate.queryForObject(SQL_FIND_ONE, new Object[] { id }, new CodeRowMapper());
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public Code saveAll(Code item) {
		assert item.getSource() != null;

		Code code = this.findById(item.getId());

		if (code == null) {
			this.jdbcTemplate.update(psc -> {
				PreparedStatement ps = psc.prepareStatement(SQL_INSERT);
				ps.setString(1, item.getId());
				ps.setString(2, item.getSource());
				return ps;
			});
			return item;
		} else {
			this.jdbcTemplate.update(psc -> {
				PreparedStatement ps = psc.prepareStatement(SQL_UPDATE);
				ps.setString(1, item.getSource());
				ps.setString(2, item.getId());
				return ps;
			});
			return code;
		}
	}

}
