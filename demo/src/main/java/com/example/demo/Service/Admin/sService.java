package com.example.demo.Service.Admin;

import com.example.demo.pojo.pageBean;
import com.example.demo.pojo.Student;

import java.io.IOException;

public interface sService {
    pageBean list(Integer page, Integer pageSize);


    pageBean listSearch(Student stu);


//    Student login(Student stu);

    Student searchByid(String sid);

    int updateStuByid(Student studata) throws IOException;

    int deleteByid(String sid);
}
