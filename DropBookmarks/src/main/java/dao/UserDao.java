package dao;

import mapper.BookMarksMapper;
import model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Optional;

public class UserDao {
    private static SqlSessionFactory sqlSessionFactory;
    private static Logger LOG =  LoggerFactory.getLogger(UserDao.class);
    public UserDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
    public  static Optional<User> getUsers(int userId) {
        User user = null;
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookMarksMapper marksMapper = session.getMapper(BookMarksMapper.class);
            user = marksMapper.getUsers(userId);
        } catch (SQLException exception) {
            LOG.error("some error occurred while performing sql operations" + exception);
        }
        return Optional.ofNullable(user);
    }
}
