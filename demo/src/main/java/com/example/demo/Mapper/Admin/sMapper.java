package com.example.demo.Mapper.Admin;

import com.example.demo.pojo.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface sMapper {


    @Select("select * from stu")
    public List<Student> list();

    @Select("select * from stu where sid=#{sid}")
    public Student getByid(String sid);

    int updateStuByid(Student studata, String id);

    int updateStu(Student student);

    List<Student> listSearch(Student stu);

   @Delete("delete from tsc.dbo.student where sid=#{sid}")
    int deleteByid(String sid);
}
