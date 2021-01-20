package com.yu.pojo;

import lombok.Data;

import java.util.List;

@Data
public class teacher {
    private int tid;
    private  String name;
    private List<student> students;
}
