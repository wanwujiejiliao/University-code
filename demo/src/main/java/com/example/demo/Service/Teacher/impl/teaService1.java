package com.example.demo.Service.Teacher.impl;

import com.example.demo.Mapper.Teacher.teaMapper;
import com.example.demo.Service.Teacher.teaService;
import com.example.demo.pojo.*;
import com.example.demo.pojo.Class;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.Service.Admin.impl.cService1.convertToSchedule;

@Service
public class teaService1 implements teaService {
    @Autowired
    private teaMapper tmapper;

    @Override
    public Teacher SearchInfo(String tid) {
        return tmapper.SearchInfo(tid);
    }

    @Override
    public pageBean listUnderReviewCourse(Integer page, Integer pageSize, String tid) {
        PageHelper.startPage(page, pageSize);
        List<Class> classes=new ArrayList<>();
        classes= tmapper.listUnderReviewCourse(tid);
//将11转换为周一第一节课
        for (Class obj : classes) {
            String input = obj.getCTime();
            String result = convertToSchedule(input);
            obj.setCTime(result);
        }

        Page<Class> p= (Page<Class>) classes;

        return new pageBean(p.getTotal(),p.getResult());
    }
    @Transactional
    @Override
    public String increaseCourse(Class course) {

        try {
             tmapper.increaseCourse(course);
        } catch (DataIntegrityViolationException e) {
            // 捕获数据完整性约束异常（包括外键约束）
            return ("Error: " + e.getMessage());
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

    @Override
    public String CancelIncrease(Class course) {
        int count=tmapper.CancelIncrease(course.getTcid());
        if(count==1)
            return null;
        else
            return "删除失败,请刷新页面后再尝试";
    }

    @Override
    public pageBean listUnApprovedCourse(Integer page, Integer pageSize, String tid) {
        PageHelper.startPage(page, pageSize);
        List<Class> classes=new ArrayList<>();
        classes= tmapper.listUnApprovedCourse(tid);
//将11转换为周一第一节课
        for (Class obj : classes) {
            String input = obj.getCTime();
            int year = obj.getTcTime().getYear();
            int month = obj.getTcTime().getMonth().getValue();
            if(month<6)
                obj.setCGrade(year+"上");
            else
                obj.setCGrade(year+"下");
            String result = convertToSchedule(input);
            obj.setCTime(result);
        }

        Page<Class> p= (Page<Class>) classes;

        return new pageBean(p.getTotal(),p.getResult());
    }

    @Override
    public pageBean listApprovedCourse(Integer page, Integer pageSize, String tid) {
        PageHelper.startPage(page, pageSize);
        List<Class> classes=new ArrayList<>();
        classes= tmapper.listApprovedCourse(tid);
        for (Class obj : classes) {
            String input = obj.getCTime();
            String result = convertToSchedule(input);
            obj.setCTime(result);
        }

        Page<Class> p= (Page<Class>) classes;

        return new pageBean(p.getTotal(),p.getResult());
    }
    @Override
    public String deleteCourse(Class course) {
        try {
            tmapper.deleteCourse(course);
        } catch (DataIntegrityViolationException e) {
            // 捕获数据完整性约束异常（包括外键约束）
            return ("Error: " +e.getMessage() );
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

    @Override
    public List<Class> timeTable(String tid) {
        List<Class> classes;
        List<Class> listClasses = new ArrayList<>();
        classes = tmapper.timeTable(tid);

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

    @Override
    public List<Build> selectBuildRoom() {
        return tmapper.selectBuildRoom();
    }

    @Override
    public List<Build> selectNotVacanrRoom(String bname) {
        List<Build> listbuild=new ArrayList<>();
        List<Build> builds = tmapper.selectNotVacanrRoom(bname);
        for (Build b : builds) {
            String[] split = b.getCTime().split("-");
            for (int i = 0; i < split.length; i++) {
                Build c1 = new Build();
                BeanUtils.copyProperties(b, c1); // 复制属性
                c1.setCTime(split[i]);
                listbuild.add(c1);
            }
        }
        return listbuild;
    }

    @Override
    public List<Build> selectBuildRooms(String bname) {
        return tmapper.selectBuildRooms(bname);
    }

    @Override
    public List<Build> selectNotVacanrRoom0(String bname) {
        List<Build> listbuild=new ArrayList<>();
        List<Build> builds = tmapper.selectNotVacanrRoom0(bname);
        for (Build b : builds) {
            String[] split = b.getCTime().split("-");
            for (int i = 0; i < split.length; i++) {
                Build c1 = new Build();
                BeanUtils.copyProperties(b, c1); // 复制属性
                c1.setCTime(split[i]);
                listbuild.add(c1);
            }
        }
        return listbuild;
    }


    @Transactional
    @Override
    public String importCourseScore(List<Score> scoreList) {
        try {
            tmapper.importCourseScore(scoreList);
        } catch (DataIntegrityViolationException e) {
            // 捕获数据完整性约束异常（包括外键约束）
            if(e.getMessage().contains("算术")) {}
            return ("Error: " +"成绩超过限制（0~100）" );
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

    @Override
    public List<Score> exportCourseScore(String cid) {
        return tmapper.exportCourseScore(cid);
    }

}
