package com.example.demo.Mapper.Admin;

import com.example.demo.pojo.Student;
import com.example.demo.pojo.Teacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface tMapper {


    @Select("select * from tch")
    public List<Teacher> list();

    @Select("select * from tch where tid=#{tid}")
    public Teacher getByid(String tid);

    int updateTeaByid(Teacher teadata, String id);

    int updateTea(Teacher teacher);

    List<Teacher> listSearch(Teacher teacher);

    @Delete("delete from tsc.dbo.teacher where tid=#{tid}")
    int deleteByid(String tid);
}
