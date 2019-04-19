package kr.ac.jejunu.userdao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;

public class UserDao {
    private final JejuJdbcTemplate jdbcTemplate;

    public UserDao(JejuJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public User get(Long id) throws SQLException {
        String sql = "select * from userinfo where id = ?";
        Object[] params = new Object[] { id };

        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
                return new User() {{
                    setId(rs.getLong("id"));
                    setName(rs.getString("name"));
                    setPassword(rs.getString("password"));
                }};
            });
        } catch (EmptyResultDataAccessException e) { }

        return user;
    }

    public Long insert(User user) throws SQLException {
        String sql = "insert into userinfo (name, password) values (?, ?)";
        Object[] params = new Object[] { user.getName(), user.getPassword() };

        return jdbcTemplate.insert(sql, params);
    }

    public void update(User user) throws SQLException {
        String sql = "update userinfo set name=?, password=? where id = ?";
        Object[] params = new Object[] { user.getName(), user.getPassword(), user.getId()};
        jdbcTemplate.update(sql, params);

    }

    public void delete(Long id) throws SQLException {
        String sql = "delete from userinfo where id = ?";
        Object[] params = new Object[] { id };
        jdbcTemplate.update(sql, params);
    }
}
