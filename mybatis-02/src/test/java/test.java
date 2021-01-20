import com.yu.dao.studentMapper;
import com.yu.dao.teacherMapper;
import com.yu.pojo.student;
import com.yu.pojo.teacher;
import com.yu.utli.MybatisUtli;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class test {
    @Test
    public  void text(){
        SqlSession sqlSession = MybatisUtli.getSqlSession();
        teacherMapper mapper = sqlSession.getMapper(teacherMapper.class);
        teacher student = mapper.getTeacher(1);
        System.out.println(student);
        sqlSession.close();
    }
    @Test
    public  void text1(){
        SqlSession sqlSession = MybatisUtli.getSqlSession();
        studentMapper mapper = sqlSession.getMapper(studentMapper.class);
        List<student> studentList = mapper.selectStudent();
        for (student student : studentList) {
            System.out.println(student);
        }
        sqlSession.close();
    }
}
