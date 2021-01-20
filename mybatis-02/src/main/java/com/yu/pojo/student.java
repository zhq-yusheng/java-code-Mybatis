package com.yu.pojo;

import lombok.Data;

@Data
public class student {
    private int id;
    private String sname;
    private teacher teacher;
}
