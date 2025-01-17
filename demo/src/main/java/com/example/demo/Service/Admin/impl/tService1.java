package com.example.demo.Service.Admin.impl;

import com.example.demo.Mapper.Admin.tMapper;
import com.example.demo.Service.Admin.tService;
import com.example.demo.pojo.Teacher;
import com.example.demo.pojo.pageBean;
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
public class tService1 implements tService {
    @Autowired
    private tMapper tmapper;
    private String  photo;
    @Override
    public pageBean list(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<Teacher> tea= tmapper.list();

        Page<Teacher> p= (Page<Teacher>) tea;

        return new pageBean(p.getTotal(),p.getResult());
    }

    @Override
    public pageBean listSearch(Teacher tea) {
        PageHelper.startPage(tea.getPage(), tea.getPageSize());

        List<Teacher> teachers= tmapper.listSearch(tea);

        Page<Teacher> p= (Page<Teacher>) teachers;

        return new pageBean(p.getTotal(),p.getResult());
    }

    @Override
    public Teacher searchByid(String tid) {
        Teacher tea=tmapper.getByid(tid);
        photo=tea.getTphoto();
        return tea;
    }

    @Override
    public int deleteByid(String tid) {
        return tmapper.deleteByid(tid);
    }

    @Override
    public int updateTeaByid(Teacher teadata) throws IOException {
        //删除旧的照片
        if(teadata.getImage()!=null){
            File file = new File("C:\\Users\\27954\\Desktop\\projects\\IdeaProjects\\demo\\file\\teacher\\"+photo);
            file.delete();
            //录入新的照片信息
            String name = teadata.getImage().getOriginalFilename();
            int i = name.lastIndexOf(".");
            String extname = name.substring(i);
            String id = String.valueOf(UUID.randomUUID())+extname;  //生成uuid作为唯一标识
            teadata.getImage().transferTo(new File("C:\\Users\\27954\\Desktop\\projects\\IdeaProjects\\demo\\file\\teacher\\"+id));
            return tmapper.updateTeaByid(teadata,id);
        }
        else {
            return tmapper.updateTea(teadata);
        }
    }
}
