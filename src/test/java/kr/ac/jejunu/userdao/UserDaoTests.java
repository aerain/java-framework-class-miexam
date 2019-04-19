package kr.ac.jejunu.userdao;

import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    @Test
    public void testGet() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "허윤호";
        String password = "1234";
        UserDao userDao = new UserDao(new JejuConnectionMaker());
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void testAdd() throws SQLException, ClassNotFoundException {
        String name = "추가";
        String password = "1234";

        UserDao userdao = new UserDao(new JejuConnectionMaker());
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        Long id = userdao.insert(user);

        User addUser = userdao.get(id);

        assertThat(addUser.getId(), is(id));
        assertThat(addUser.getName(), is(name));
        assertThat(addUser.getPassword(), is(password));
    }
    @Test
    public void testHallaGet() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "헐크";
        String password = "1111";
        UserDao userDao = new UserDao(new HallaConnectionMaker());
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void testHallaAdd() throws SQLException, ClassNotFoundException {
        String name = "추가";
        String password = "1234";

        UserDao userdao = new UserDao(new HallaConnectionMaker());
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        Long id = userdao.insert(user);

        User addUser = userdao.get(id);

        assertThat(addUser.getId(), is(id));
        assertThat(addUser.getName(), is(name));
        assertThat(addUser.getPassword(), is(password));
    }
}