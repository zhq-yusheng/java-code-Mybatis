package com.yu.dao;

import com.yu.pojo.student;
import java.util.List;

public interface studentMapper {
    List<student> selectStudent();
    List<student> selectStudentTwo();
}
