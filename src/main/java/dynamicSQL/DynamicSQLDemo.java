package dynamicSQL;

import domain.QueryIds;
import domain.User;


import java.util.List;

public interface DynamicSQLDemo {
        //动态sql查询
        //查询方法
        List<User> findAll();
        List<User> findByUserCondition(User user);
        List<User> findByIds(QueryIds queryIds);
    }

