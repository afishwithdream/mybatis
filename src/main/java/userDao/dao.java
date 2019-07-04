package userDao;

import domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface dao {
    @Select("select * from tab_user")
    public List<User> findAll();
}
