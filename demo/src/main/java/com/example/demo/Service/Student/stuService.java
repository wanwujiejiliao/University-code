package com.example.demo.Service.Student;

import com.example.demo.pojo.Class;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.pageBean;

import java.util.List;

public interface stuService {
    List<Class> listCurrentSelectedClass(String sid);

    pageBean listClass(Integer page, Integer pageSize,String sid);

    pageBean listSearchClass(Class aClass);

    String Toselect(String cid, String sid);

    Student SearchInfo(String sid);

    pageBean listCurrentSelectedClasses(Integer page, Integer pageSize, String sid);

    int deleteClass(String cid, String sid);

    pageBean listSelectedClasses(Integer page, Integer pageSize, String sid);

    pageBean ListSelectedCourseSearch(Class aClass);
}