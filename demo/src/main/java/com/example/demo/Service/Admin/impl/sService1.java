package com.example.demo.Service.Admin.impl;

import com.example.demo.Mapper.Admin.sMapper;
import com.example.demo.Service.Admin.sService;
import com.example.demo.pojo.pageBean;
import com.example.demo.pojo.Student;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class sService1 implements sService {
    @Autowired
    private sMapper smapper;
    private String  photo;

    @Override
    public pageBean list(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<Student> stu= smapper.list();

        Page<Student> p= (Page<Student>) stu;

        return new pageBean(p.getTotal(),p.getResult());
    }

    @Override
    public pageBean listSearch(Student stu) {
        PageHelper.startPage(stu.getPage(), stu.getPageSize());

        List<Student> students= smapper.listSearch(stu);

        Page<Student> p= (Page<Student>) students;

        return new pageBean(p.getTotal(),p.getResult());
    }

//    @Override
//    public Student login(Student stu) {
//        return smapper.getByidandpwd (stu);
//    }

    @Override
    public Student searchByid(String sid) {
        Student stu=smapper.getByid(sid);
        photo=stu.getSphoto();
        return stu;
    }

    @Override
    public int updateStuByid(Student studata) throws IOException {
        //删除旧的照片
        if(studata.getImage()!=null){
            File file = new File("C:\\Users\\27954\\Desktop\\projects\\IdeaProjects\\demo\\file\\student\\"+photo);
            file.delete();
            //录入新的照片信息
            String name = studata.getImage().getOriginalFilename();
            int i = name.lastIndexOf(".");
            String extname = name.substring(i);
            String id = String.valueOf(UUID.randomUUID())+extname;  //生成uuid作为唯一标识
            studata.getImage().transferTo(new File("C:\\Users\\27954\\Desktop\\projects\\IdeaProjects\\demo\\file\\student\\"+id));
            return smapper.updateStuByid(studata,id);
        }
        else {
            return smapper.updateStu(studata);
        }

    }

    @Override
    public int deleteByid(String sid) {
        return smapper.deleteByid(sid);
    }
}
