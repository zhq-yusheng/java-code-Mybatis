package com.yu.dao;

import com.yu.pojo.User;
import com.yu.utli.MybatisUtli;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;

public class mybatisTest {
    static Logger logger =Logger.getLogger(mybatisTest.class);
    @Test
    public void text(){
        SqlSession sqlSession = MybatisUtli.getSqlSession();
        userMapper mapper = sqlSession.getMapper(userMapper.class);
        List<User> students = mapper.selectStudent();
        for (User student : students) {
            System.out.println(student);
        }
        sqlSession.close();
    }
    @Test
    public void addStudentTest(){
        //如果执行的增删改的操作就要提交事务 sqlSession.commit();
        //使用日志类进行记录
        SqlSession sqlSession = MybatisUtli.getSqlSession();
        logger.info("获取sqlSession对象");
        userMapper mapper = sqlSession.getMapper(userMapper.class);
        mapper.addStudent(new User("赵八",25,1.45));
        logger.info("执行sql");
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void ZJtext(){
        SqlSession sqlSession = MybatisUtli.getSqlSession();
        userMapper mapper = sqlSession.getMapper(userMapper.class);
        User user = mapper.selectOne("zz");
        System.out.println(user);
        sqlSession.close();
    }
}
