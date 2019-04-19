package kr.ac.jejunu.userdao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class DaoFactory {
    @Value(value="${db.class}")
    private String className;
    @Value(value="${db.url}")
    private String url;
    @Value(value="${db.user}")
    private String user;
    @Value(value="${db.password}")
    private String password;

    @Bean
    public UserDao userDao() {
        return new UserDao(context());
    }

    private Context context() {
        return new Context(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName(className));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }
}