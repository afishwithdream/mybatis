package userDao;

import domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface dao {
    //注解,sql语句
    @Select("select * from tab_user")
    //查询方法
    List<User> findAll();
    //insert into 参数,#{实体类属性.}
    @Insert({"insert into tab_user (name,birthday,gender,addr) values(#{name},#{birthday},#{gender},#{addr})"})
    void addUser(User user);
    @Update({"update tab_user set name=#{name}where id = #{id}"})
    void update(User user);
}
