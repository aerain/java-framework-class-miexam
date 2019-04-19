package kr.ac.jejunu.userdao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    private UserDao userdao;

    @Before
    public void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userdao = applicationContext.getBean("userDao", UserDao.class);
    }
    @Test
    public void testGet() throws SQLException{
        Long id = 1l;
        String name = "허윤호";
        String password = "1234";
        User user = userdao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }
    @Test
    public void testAdd() throws SQLException {
        String name = "추가";
        String password = "1234";
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
    public void testUpdate() throws SQLException {
        String name = "추가업뎃";
        String password = "123123";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Long id = userdao.insert(user);

        String willUpdateName = "바뀐업뎃";
        String willUpdatePassword = "12313444";
        User willUpdateUser = userdao.get(id);
        willUpdateUser.setName(willUpdateName);
        willUpdateUser.setPassword(willUpdatePassword);

        userdao.update(willUpdateUser);

        User updatedUser = userdao.get(id);
        assertThat(updatedUser.getId(), is(id));
        assertThat(updatedUser.getName(), is(willUpdateName));
        assertThat(updatedUser.getPassword(), is(willUpdatePassword));
    }

    @Test
    public void testDelete() throws SQLException {
        String name = "줬따뺐기";
        String password = "12345";
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Long id = userdao.insert(user);
        userdao.delete(id);
        User deletedUser = userdao.get(id);
        assertThat(deletedUser, is(nullValue()));
    }
//    @Test
//    public void testHallaGet() throws SQLException, ClassNotFoundException {
//        Long id = 1l;
//        String name = "헐크";
//        String password = "1111";
//        UserDao userDao = new UserDao(new HallaConnectionMaker());
//        User user = userDao.get(id);
//        assertThat(user.getId(), is(id));
//        assertThat(user.getName(), is(name));
//        assertThat(user.getPassword(), is(password));
//    }
//
//    @Test
//    public void testHallaAdd() throws SQLException, ClassNotFoundException {
//        String name = "추가";
//        String password = "1234";
//
//        UserDao userdao = new UserDao(new HallaConnectionMaker());
//        User user = new User();
//        user.setName(name);
//        user.setPassword(password);
//
//        Long id = userdao.insert(user);
//
//        User addUser = userdao.get(id);
//
//        assertThat(addUser.getId(), is(id));
//        assertThat(addUser.getName(), is(name));
//        assertThat(addUser.getPassword(), is(password));
//    }
}