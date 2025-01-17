package com.example.demo.Mapper.Login;

import com.example.demo.pojo.Student;
import com.example.demo.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface LMapper {
//    学生登陆
    @Select("select count(*) from student where sid=#{id} and spwd=#{password}")
    public int slogin(String id, String password);
//    教师登录
    @Select("select count(*) from tsc.dbo.teacher where tid=#{id} and tpwd=#{password}")
    public int tlogin(String id, String password);
//    管理员登陆
    @Select("select count(*) from tsc.dbo.admin where aid=#{id} and apwd=#{password}")
    public int alogin(String id, String password);
//  管理员修改密码
    @Update("update admin set tsc.dbo.admin.apwd=#{password} where tsc.dbo.admin.aid=#{id}")
    int amodify(String id, String password);
//    学生修改密码
    @Update("update tsc.dbo.student set tsc.dbo.student.spwd=#{password} where tsc.dbo.student.sid=#{id}")
    int smodify(String id, String password);
//    教师修改密码
    @Update("update  tsc.dbo.teacher set tsc.dbo.teacher.tpwd=#{password} where tsc.dbo.teacher.tid=#{tid}")
    int tmodify(String id, String password);
    @Select("select sid from tsc.dbo.status where  sname='选课信息'")
    int status();
}
