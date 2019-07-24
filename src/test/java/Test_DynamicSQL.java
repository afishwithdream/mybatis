import domain.User;
import dynamicSQL.DynamicSQLDemo;
import domain.QueryIds;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Test_DynamicSQL  {
    private InputStream inputStream;
    private SqlSession sqlSession;
    public DynamicSQLDemo getDynamicSQLDemo() throws IOException {
        inputStream = Resources.getResourceAsStream("sqlConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        DynamicSQLDemo dynamicSQLDemo = sqlSession.getMapper(DynamicSQLDemo.class);
        return dynamicSQLDemo;
    }
    public void commit() throws IOException {
        inputStream.close();
        sqlSession.commit();
    }
    @Test
    public void findAll() throws IOException {
        //基于xml查询
        DynamicSQLDemo dynamicSQLDemo = getDynamicSQLDemo();
        List<User> user = dynamicSQLDemo.findAll();
        for (User user1 : user) {
            System.out.println(user1);
        }
        commit();
    }
    @Test
    public void findByUserCondition() throws IOException {
        User u = new User();
        u.setName("张三");
        List<User> list = getDynamicSQLDemo().findByUserCondition(u);
        for (User user : list) {
            System.out.println(user);
        }
    }
    @Test
    public void findByIds() throws IOException {
        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(4);
        //创建Integer集合
        QueryIds queryIds = new QueryIds();
        //设置参数
        queryIds.setIds(list);
        //将集合传入,查询遍历结果
        List<User> l = getDynamicSQLDemo().findByIds(queryIds);

        for (User user : l) {
            System.out.println(user);
        }
    }
}
