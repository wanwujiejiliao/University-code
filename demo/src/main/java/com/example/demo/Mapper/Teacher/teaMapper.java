package com.example.demo.Mapper.Teacher;

import com.example.demo.pojo.Build;
import com.example.demo.pojo.Class;
import com.example.demo.pojo.Score;
import com.example.demo.pojo.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface teaMapper {
    @Select("select * from tch where tid=#{tid}")
    Teacher SearchInfo(String tid);

    List<Class> listUnderReviewCourse(String tid);

    int increaseCourse(Class course);
    @Update("update teacher_course set ado=-1,a_info='取消申请' where tcid=#{tcid} and ado=0")
    int CancelIncrease(Integer tcid);

    List<Class> listUnApprovedCourse(String tid);

    List<Class> listApprovedCourse(String tid);

    void importCourseScore(@Param("scoreList") List<Score> scoreList);

    List<Score> exportCourseScore(String cid);

    void deleteCourse(Class course);

    List<Class> timeTable(String tid);

    @Select("select distinct bname from tsc.dbo.build ")
    List<Build> selectBuildRoom();

    List<Build> selectNotVacanrRoom(String bname);

    @Select("select bname,b_room from tsc.dbo.build where bname=#{bname}")
    List<Build> selectBuildRooms(String bname);

    List<Build> selectNotVacanrRoom0(String bname);
}
