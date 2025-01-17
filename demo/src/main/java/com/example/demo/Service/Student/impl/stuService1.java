package com.example.demo.Service.Student.impl;

import com.example.demo.pojo.Student;
import com.microsoft.sqlserver.jdbc.SQLServerException ;
import com.example.demo.Mapper.Student.stuMapper;
import com.example.demo.Service.Student.stuService;
import com.example.demo.pojo.Class;
import com.example.demo.pojo.pageBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.Service.Admin.impl.cService1.convertToSchedule;

@Service
@Slf4j
public class stuService1 implements stuService {
    @Autowired
    stuMapper smapper;
    //分割时间
    @Override
    public List<Class> listCurrentSelectedClass(String sid) {
        List<Class> classes;
        List<Class> listClasses = new ArrayList<>();
        classes = smapper.listCurrentSelectedClass(sid);

        for (Class c : classes) {
            String[] split = c.getCTime().split("-");
            for (int i = 0; i < split.length; i++) {
                Class c1 = new Class();
                BeanUtils.copyProperties(c, c1); // 复制属性
                c1.setCTime(split[i]);
                listClasses.add(c1);
            }
        }
        return listClasses;
    }
    //未分割时间
    @Override
    public pageBean listCurrentSelectedClasses(Integer page, Integer pageSize, String sid) {
        PageHelper.startPage(page, pageSize);

        List<Class> classes= smapper.listCurrentSelectedClasses(sid);

        for (Class obj : classes) {
            String input = obj.getCTime();
            String result = convertToSchedule(input);
            obj.setCTime(result); // 输出转换结果
        }
        Page<Class> p= (Page<Class>) classes;

        return new pageBean(p.getTotal(),p.getResult());
    }

    //查询可选课程
    @Override
    public pageBean listClass(Integer page, Integer pageSize,String sid) {
        PageHelper.startPage(page, pageSize);
        List<Class> classes=new ArrayList<>();
        classes= smapper.listClass(sid);

        for (Class obj : classes) {
            String input = obj.getCTime();
            String result = convertToSchedule(input);
            obj.setCTime(result); // 输出转换结果
        }

        Page<Class> p= (Page<Class>) classes;

        return new pageBean(p.getTotal(),p.getResult());
    }
//模糊查询可选课程
    @Override
    public pageBean listSearchClass(Class aClass) {
        PageHelper.startPage(aClass.getPage(), aClass.getPageSize());
        String address;
        if(aClass.getCroom()!=null)
            address= aClass.getCbuild()+aClass.getCroom();
        else
            address= aClass.getCbuild();
        aClass.setCAddress(address);

        log.info(aClass.toString());
        List<Class> classes= smapper.listSearchClass(aClass);

        for (Class obj : classes) {
            String input = obj.getCTime();
            String result = convertToSchedule(input);
            obj.setCTime(result); // 输出转换结果
        }
        Page<Class> p= (Page<Class>) classes;

        return new pageBean(p.getTotal(),p.getResult());
    }
    //选课
    @Override
    public String Toselect(String cid, String sid) {
        int count = 0;

        try {
            count = smapper.Toselect(cid, sid);
        } catch (DataIntegrityViolationException e) {
            // 捕获数据完整性约束异常（包括外键约束）
            System.err.println("插入数据违反了完整性约束！");
        } catch (UncategorizedSQLException ex) {
            Throwable cause = ex.getCause();
            if (cause instanceof SQLServerException) {
                SQLServerException sqlEx = (SQLServerException) cause;
                String errorMessage = sqlEx.getMessage();
                // 处理具体的 SQL 错误消息
                return ("Error: " + errorMessage);
            }
        }
        return null;
    }
    //退课
    @Override
    public int deleteClass(String cid, String sid) {
        int count = 0;
        try {
            count = smapper.deleteClass(cid, sid);
        } catch (DataIntegrityViolationException e) {
            // 捕获数据完整性约束异常（包括外键约束）
            System.err.println("插入数据违反了完整性约束！");
        }
        return count;
    }
//全部学期已选课程
    @Override
    public pageBean listSelectedClasses(Integer page, Integer pageSize, String sid) {
        PageHelper.startPage(page, pageSize);


        List<Class> classes= smapper.listSelectedClasses(sid);

        for (Class obj : classes) {
            String input = obj.getCTime();
            String result = convertToSchedule(input);
            obj.setCTime(result); // 输出转换结果
        }
        Page<Class> p= (Page<Class>) classes;

        return new pageBean(p.getTotal(),p.getResult());
    }
    //模糊查询全部学期已选课程
    @Override
    public pageBean ListSelectedCourseSearch(Class aClass) {
        PageHelper.startPage(aClass.getPage(), aClass.getPageSize());
        String address;
        if(aClass.getCroom()!=null)
            address= aClass.getCbuild()+aClass.getCroom();
        else
            address= aClass.getCbuild();
        aClass.setCAddress(address);
        List<Class> classes= smapper.ListSelectedCourseSearch(aClass);

        for (Class obj : classes) {
            String input = obj.getCTime();
            String result = convertToSchedule(input);
            obj.setCTime(result); // 输出转换结果
        }
        Page<Class> p= (Page<Class>) classes;

        return new pageBean(p.getTotal(),p.getResult());
    }

    @Override
    public Student SearchInfo(String sid) {
        return smapper.SearchInfo(sid);
    }

}
