import com.night.pojo.UserPojo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test {
    @org.junit.Test
    public void Test(){
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream resourceAsFile = null;
        try {
            resourceAsFile = Resources.getResourceAsStream("sqlmapconfig.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory factory = sqlSessionFactoryBuilder.build(resourceAsFile);
        SqlSession sqlSession = factory.openSession();
        List<UserPojo> findAll = sqlSession.selectList("findAll");

        System.out.println(findAll);

        sqlSession.close();
    }
    @org.junit.Test
    public void Test2(){
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream resourceAsFile = null;
        try {
            resourceAsFile = Resources.getResourceAsStream("sqlmapconfig.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory factory = sqlSessionFactoryBuilder.build(resourceAsFile);
        SqlSession sqlSession = factory.openSession();
        List<UserPojo> findAll = sqlSession.selectList("findById", 6);

        System.out.println(findAll);

        sqlSession.close();
    }
}
