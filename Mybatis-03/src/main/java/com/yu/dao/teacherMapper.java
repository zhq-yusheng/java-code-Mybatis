package com.yu.dao;


import com.yu.pojo.teacher;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface teacherMapper {
   List<teacher> getStudent(@Param("tid")int id);
}
