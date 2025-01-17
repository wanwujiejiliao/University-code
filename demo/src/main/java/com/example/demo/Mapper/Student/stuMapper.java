package com.example.demo.Mapper.Student;

import com.example.demo.pojo.Class;
import com.example.demo.pojo.Student;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface stuMapper {


    List<Class> listClass(String sid);


    List<Class> listCurrentSelectedClass(String sid);

    List<Class> listSearchClass(Class aClass);

    @Insert("insert into student_course_info(cid,sid,cdo) values(#{cid},#{sid},'选课')")
    int Toselect(String cid, String sid);

    @Select("select * from stu where sid=#{sid}")
    Student SearchInfo(String sid);

    List<Class> listCurrentSelectedClasses(String sid);

    @Insert("insert into student_course_info(cid,sid,cdo) values(#{cid},#{sid},'退课')")
    int deleteClass(String cid, String sid);

    List<Class> listSelectedClasses(String sid);

    List<Class> ListSelectedCourseSearch(Class aClass);
}
