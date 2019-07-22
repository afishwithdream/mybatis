import org.junit.Test;
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
    @Test
    public void findAll() throws IOException {
        /*
         * 1.读取流对象
         * 2.创建SqlSessionFactoryBuilder,使用SqlSessionFactoryBuilder创建工厂类
         * 3.使用工厂类生产SqlSession
         * 4.代理模式增强Dao接口
         * 5.查询数据库
         *
         * 6.关流
         * */
        //获取配置文件的流对象
        InputStream inputStream = Resources.getResourceAsStream("sqlConfig.xml");
       /* byte arr [] = new byte[1024];
        while ((len = inputStream.read(arr)) != -1){
            System.out.println(new String(arr,0,len));
        }*/
        //  SqlSessionFactory是接口,用它的实现类SqlSessionFactoryBuilder创建工厂类
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //使用构建者模式创建
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        //使用工厂模式生产能操作数据库的sqlSession对象
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
    @Test
    public void addUser() throws IOException {
        User user = new User();
        user.setName("lol");
        user.setGender("男");
        user.setAddr("苏州");
        user.setBirthday("1996-12-05");
        //加载配置流文件
        InputStream inputStream = Resources.getResourceAsStream("sqlConfig.xml");
        //创建类
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //创建操作数据库的sqlsession对象.
        SqlSession sqlSession = sqlSessionFactory.openSession();
        dao dao = sqlSession.getMapper(userDao.dao.class);
        //添加到数据库
        dao.addUser(user);
        //提交事务,不然mysql开始事务后会回滚
        sqlSession.commit();
        //关流
        inputStream.close();
        sqlSession.close();
    }
    @Test
    public void update() throws IOException {
        User user = new User();
        //设置要修改的Id
        user.setId(7);
        //修改的项
        user.setName("dnf");
        InputStream inputStream = Resources.getResourceAsStream("sqlConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        dao dao = sqlSession.getMapper(userDao.dao.class);
        //更新
        dao.update(user);
        //提交事务 关流
        sqlSession.commit();
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void del() throws IOException {
        User user = new User();
        //设置要删除的Id
        user.setId(2);
        InputStream inputStream = Resources.getResourceAsStream("sqlConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        dao dao = sqlSession.getMapper(userDao.dao.class);
        //删除
        dao.del(user);
        //提交事务 关流
        sqlSession.commit();
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void selectById() throws IOException {
        /*User user = new User()
        //设置查询的Id
        模糊查询,("%aa%");
        user.setId(7);*/
        InputStream inputStream = Resources.getResourceAsStream("sqlConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        dao dao = sqlSession.getMapper(userDao.dao.class);
        //更新
        List<User> list = dao.selectById(7);
        for (User user1 : list) {
            System.out.println(user1);
        }
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void selectLike() throws IOException {
        InputStream resources = Resources.getResourceAsStream("sqlConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resources);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        dao dao = sqlSession.getMapper(userDao.dao.class);
        List<User> list = dao.selectLike("%京%");
        for (User user : list) {
            System.out.println(user);
        }
    }
    /*
    * 解决数据库字段与实体类查询条件名称不一致
    * 1.别名
    *   select user_name as userName from tab_user;
    * 2.配置查询结果的列名和实体类属性名的对应关系
    *   注解:
    *   @Results(
    *       @Result(property="userName",column="user_name");
    *           ...
    * )
    *   配置XML文件
    * */
}
