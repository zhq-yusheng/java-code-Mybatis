package com.yu.dao;
import com.yu.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface userMapper {
 List<User> selectStudent();
 void addStudent(User stu);
 @Select("select * from student where name=#{Uname}")
 User selectOne(@Param("Uname") String name);
 //如果参数是基本类型就得加上注解@Param起个名字xml或者注解拿值就拿这个名字
}
