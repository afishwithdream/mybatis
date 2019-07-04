import userDao.dao;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test_mybatis {
    public static void main(String[] args) throws IOException {
        /*
        * 1.读取流对象
        * 2.创建SqlSessionFactoryBuilder,使用SqlSessionFactoryBuilder创建工厂类
        * 3.使用工厂类生产SqlSession
        * 4.代理模式增强Dao接口
        * 5.查询数据库
        * 6.关流
        * */
        //获取流对象
        InputStream inputStream = Resources.getResourceAsStream("sqlConfig.xml");
       /* byte arr [] = new byte[1024];
        while ((len = inputStream.read(arr)) != -1){
            System.out.println(new String(arr,0,len));
        }*/
       //  SqlSessionFactory是接口,用它的实现类SqlSessionFactoryBuilder创建工厂类
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //使用构建者模式创建
        SqlSessionFactory sqlSessionFactory= builder.build(inputStream);
        //使用工厂模式生产sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //代理模式,创建Dao实现类,对已有借口实现增强
        dao dao = sqlSession.getMapper(userDao.dao.class);
        List<User> list = dao.findAll();
        for (User user : list) {
            System.out.println(user);
        }
        //关流
        sqlSession.close();
        inputStream.close();
    }
}
