package com.example.demo.Service.Admin;

import com.example.demo.pojo.Class;
import com.example.demo.pojo.pageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

public interface cService {
    pageBean list(Integer page, Integer pageSize,Integer type);

    pageBean listSearch0(Class aClass);

    int updategood(Class aClass);

    int updateno(Class aClass);

    void openSelect();

    void openLecture();

    void openEnter();

    void openEnd();

    void openAudit();

    int getStatus();
}
