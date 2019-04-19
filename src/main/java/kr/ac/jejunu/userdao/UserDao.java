package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final Context context;
    public UserDao(Context context) {
        this.context = context;
    }
    public User get(Long id) throws SQLException {
        StatementStrategy statementStrategy = new GetStatementStrategy(id);
        return context.getContext(statementStrategy);
    }
    public Long insert(User user) throws SQLException {
        StatementStrategy statementStrategy = new InsertStatementStrategy(user);
        return context.insertContext(statementStrategy);
    }

    public void update(User user) throws SQLException {
        StatementStrategy statementStrategy = new UpdateStatementStrategy(user);
        context.updateContext(statementStrategy);

    }
    public void delete(Long id) throws SQLException {
        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);
        context.updateContext(statementStrategy);
    }
}
