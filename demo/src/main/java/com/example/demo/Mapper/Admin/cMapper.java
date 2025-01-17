package com.example.demo.Mapper.Admin;

import com.example.demo.pojo.Class;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface cMapper {
    @Select("select * from admin0")
    List<Class> list0();
    @Select("select * from admin1")
    List<Class> list1();
    @Select("select * from admin_1 order by  tcid desc")
    List<Class> list_1();
    @Select("select * from admin2")
    List<Class> list2();
    @Select("select * from admin3")
    List<Class> list3();
    @Select("select * from admin4")
    List<Class> list4();
    @Select("select * from admin5 order by c_grade desc")
    List<Class> list5();

    List<Class> listSearch5(Class aClass);

    List<Class> listSearch0(Class aClass);
    //审核同意
    @Update("update tsc.dbo.teacher_course set ado=1 where tcid=#{tcid}")
    int updategood(Class aClass);
    //审核不同意 并输入原因
    @Update("update tsc.dbo.teacher_course set ado=-1 , a_info=#{aInfo} where tcid=#{tcid}")
    int updateno(Class aClass);

    @Update("update course set tsc.dbo.course.cstatus=2 where tsc.dbo.course.cstatus=1;" +
            "update tsc.dbo.status set sid=12 where sname='选课信息'")
    void openSelect();

    @Update("update course set tsc.dbo.course.cstatus=3 where tsc.dbo.course.cstatus=2;" +
            "update tsc.dbo.status set sid=13 where sname='选课信息'")
    void openLecture();

    @Update("update course set tsc.dbo.course.cstatus=4 where tsc.dbo.course.cstatus=3;" +
            "update tsc.dbo.status set sid=14 where sname='选课信息'")
    void openEnter();

    @Update("update course set tsc.dbo.course.cstatus=5 where tsc.dbo.course.cstatus=4;" +
            "update tsc.dbo.status set sid=10 where sname='选课信息'")
    void openEnd();

    @Update("update tsc.dbo.status set sid=11 where sname='选课信息'")
    void openAudit();

    @Select("select sid from tsc.dbo.status where sname='选课信息'")
    int getStatus();

    List<Class> listSearch_1(Class aClass);
}
