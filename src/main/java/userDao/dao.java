package userDao;

import domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface dao {
    //注解,sql语句
    //@Select("select * from tab_user")
    //查询方法
    List<User> findAll();
    //insert into 参数,#{实体类属性.}
    @Insert({"insert into tab_user (name,birthday,gender,addr) values(#{name},#{birthday},#{gender},#{addr})"})
    void addUser(User user);
    @Update({"update tab_user set name=#{name}where id = #{id}"})
    void update(User user);
    @Delete({"delete from tab_user where id = #{id}"})
    void del(User user);
    //id不与实体类一致,占位符. 执行语句的时候可以填入自己的参数
    @Select({"select * from tab_user where id = #{aid}"})
    List<User> selectById(Integer id);
    @Select({"select * from tab_user where addr like#{myCondition}"})
    List<User> selectLike(String addr);
}
