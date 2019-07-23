import domain.User;
import dynamicSQL.DynamicSQLDemo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.lf5.util.Resource;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
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
    public void findByUserCondition(){

    }
}
