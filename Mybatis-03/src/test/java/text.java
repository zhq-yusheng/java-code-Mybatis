import com.yu.dao.teacherMapper;
import com.yu.mapper.AddStudentMapper;
import com.yu.pojo.teacher;
import com.yu.utli.MybatisUtli;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class text {
    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtli.getSqlSession();
        teacherMapper mapper = sqlSession.getMapper(teacherMapper.class);
        List<teacher> student = mapper.getStudent(1);
        System.out.println(student);
        sqlSession.close();
    }
    @Test
    public void test1(){
        SqlSession sqlSession = MybatisUtli.getSqlSession();
        AddStudentMapper mapper = sqlSession.getMapper(AddStudentMapper.class);
        mapper.addStudent(new Student("2017","张三","男",1001,"ssssssss"));
    }

}
