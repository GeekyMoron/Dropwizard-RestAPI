package mapper;

import model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;

@Mapper
public interface BookMarksMapper {
   @Select("SELECT * from users where id = #{id}")
   User getUsers(@Param("id") int userId) throws SQLException;
}
