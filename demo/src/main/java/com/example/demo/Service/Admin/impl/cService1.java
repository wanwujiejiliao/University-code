package com.example.demo.Service.Admin.impl;

import com.example.demo.Mapper.Admin.cMapper;
import com.example.demo.Service.Admin.cService;
import com.example.demo.pojo.Class;
import com.example.demo.pojo.Teacher;
import com.example.demo.pojo.pageBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class cService1 implements cService {
    @Autowired
    private cMapper cmapper;
    @Override
    public pageBean list(Integer page, Integer pageSize,Integer type) {
        PageHelper.startPage(page, pageSize);
        List<Class> classes=new ArrayList<>();
        if(type.equals(0)){
            classes= cmapper.list0();
        }
        else if(type.equals(1)){
            classes= cmapper.list1();
        }
        else if(type.equals(-1)){
            classes= cmapper.list_1();
        }
        else if(type.equals(2)){
            classes= cmapper.list2();
        }
        else if(type.equals(3)){
            classes= cmapper.list3();
        }
        else if(type.equals(4)){
            classes= cmapper.list4();
        }
        else if(type.equals(5)){
            classes= cmapper.list5();
        }

        for (Class obj : classes) {
            String input = obj.getCTime();
            String result = convertToSchedule(input);
            obj.setCTime(result); // 输出转换结果
        }

        Page<Class> p= (Page<Class>) classes;

        return new pageBean(p.getTotal(),p.getResult());
    }

    @Override
    public pageBean listSearch0(Class aClass) {
        PageHelper.startPage(aClass.getPage(), aClass.getPageSize());
        String address;
        if(aClass.getCroom()!=null)
            address= aClass.getCbuild()+aClass.getCroom();
        else
            address= aClass.getCbuild();
        aClass.setCAddress(address);

        log.info(aClass.toString());
        List<Class> classes = null;
        if(aClass.getType()==5)
        {
            classes= cmapper.listSearch5(aClass);
        }
        else if (aClass.getType()==-1)
        {
            classes= cmapper.listSearch_1(aClass);
        }
        else{
            classes= cmapper.listSearch0(aClass);
        }
        for (Class obj : classes) {
            String input = obj.getCTime();
            String result = convertToSchedule(input);
            obj.setCTime(result); // 输出转换结果
        }
        Page<Class> p= (Page<Class>) classes;

        return new pageBean(p.getTotal(),p.getResult());
    }

    @Override
    public int updategood(Class aClass) {
        return cmapper.updategood(aClass);
    }

    @Override
    public int updateno(Class aClass) {
        return cmapper.updateno(aClass);
    }

    @Override
    public void openSelect() {
        cmapper.openSelect();
    }

    @Override
    public void openLecture() {
        cmapper.openLecture();
    }

    @Override
    public void openEnter() {
        cmapper.openEnter();
    }

    @Override
    public void openEnd() {
        cmapper.openEnd();
    }

    @Override
    public void openAudit() {
        cmapper.openAudit();
    }

    @Override
    public int getStatus() {
        return cmapper.getStatus();
    }

    //将11 变为周一第一节课
    public static String convertToSchedule(String input) {
        String[] parts = input.split("-");
        StringBuilder result=new StringBuilder();
        result.append("");
        String[] weekdays = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
        String[] sessions = {"第一节课", "第二节课", "第三节课", "第四节课", "第五节课","第六节课"};

        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            int weekday = Integer.parseInt(String.valueOf(part.charAt(0)))-1;
            int session = Integer.parseInt(String.valueOf(part.charAt(1)))-1;
            result.append(weekdays[weekday]+sessions[session]) ;
        }

        return result.toString();
    }
}
