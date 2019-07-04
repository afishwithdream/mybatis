import Userdao.dao;
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
        InputStream inputStream = Resources.getResourceAsStream("sqlConfig.xml");
       /* byte arr [] = new byte[1024];
        int len =0;
        while ((len = inputStream.read(arr)) != -1){
            System.out.println(new String(arr,0,len));
        }*/
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory= builder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        dao dao = sqlSession.getMapper(Userdao.dao.class);
        List<User> list = dao.findAll();
        for (User user : list) {
            System.out.println(user);
        }
    }
}
