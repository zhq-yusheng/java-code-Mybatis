package com.yu.dao;

import com.yu.pojo.teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface teacherMapper {
    @Select("select * from teacher where tid=#{tid}")
    teacher getTeacher(@Param("tid") int id);
}
