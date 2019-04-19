package kr.ac.jejunu.userdao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class JejuJdbcTemplate extends JdbcTemplate {
    public JejuJdbcTemplate(DataSource dataSource) {
        super(dataSource);
    }

    Long insert(String sql, Object[] params) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++)
                preparedStatement.setObject(i + 1, params[i]);
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }
}