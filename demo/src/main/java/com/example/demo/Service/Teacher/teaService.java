package com.example.demo.Service.Teacher;

import com.example.demo.pojo.*;
import com.example.demo.pojo.Class;

import java.util.List;

public interface teaService {
    Teacher SearchInfo(String tid);

    pageBean listUnderReviewCourse(Integer page, Integer pageSize, String tid);

    String increaseCourse(Class course);

    String CancelIncrease(Class course);

    pageBean listUnApprovedCourse(Integer page, Integer pageSize, String tid);

    pageBean listApprovedCourse(Integer page, Integer pageSize, String tid);

    String importCourseScore(List<Score> scoreList);

    List<Score> exportCourseScore(String cid);

    String deleteCourse(Class course);

    List<Class> timeTable(String tid);

    List<Build> selectBuildRoom();

    List<Build> selectNotVacanrRoom(String bname);

    List<Build> selectBuildRooms(String bname);

    List<Build> selectNotVacanrRoom0(String bname);
}
