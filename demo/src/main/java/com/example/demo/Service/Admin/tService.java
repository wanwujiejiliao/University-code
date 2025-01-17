package com.example.demo.Service.Admin;

import com.example.demo.pojo.Teacher;
import com.example.demo.pojo.pageBean;

import java.io.IOException;

public interface tService {

    pageBean list(Integer page, Integer pageSize);

    pageBean listSearch(Teacher tea);

    Teacher searchByid(String tid);

    int deleteByid(String tid);

    int updateTeaByid(Teacher teadata) throws IOException;
}
