package com.example.demo.Controll.student;

import com.example.demo.Service.Admin.cService;
import com.example.demo.Service.Student.stuService;
import com.example.demo.pojo.Class;
import com.example.demo.pojo.Result;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.pageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/student")
public class hasclassControll {
    @Autowired
    private stuService stuService;
//查询全部学期已选课程
    @GetMapping("/SelectedClasses")
    public Result SelectedClass(@RequestParam Integer page, @RequestParam Integer pageSize,@RequestParam String sid){
        log.info(String.valueOf(sid));
        pageBean p=stuService.listSelectedClasses(page,pageSize,sid);
        return Result.success(p);
    }
//    查询本学期已选课程
    //分割时间版
    @GetMapping("/CurrentselectedClass")
    public Result CurrentselectedClass(@RequestParam String sid){
        log.info(sid);
        List<Class> c =stuService.listCurrentSelectedClass(sid);
        return Result.success(c);
    }
    //未分割时间版
    @GetMapping("/CurrentselectedClasses")
    public Result CurrentselectedClasses(@RequestParam Integer page, @RequestParam Integer pageSize,@RequestParam String sid){
        log.info(String.valueOf(sid));
        pageBean p=stuService.listCurrentSelectedClasses(page,pageSize,sid);
        return Result.success(p);
    }
    //查询可选课程
    @GetMapping("/CourseSelect")
    public Result SearchAll0(@RequestParam Integer page, @RequestParam Integer pageSize,@RequestParam String sid){
        pageBean p =stuService.listClass(page,pageSize,sid);
        log.info(p.toString());
        return Result.success(p);
    }
    //模糊查询课程
    @PostMapping("/CourseSearch")
    public Result Search(@RequestBody Class Class) {
        log.info(Class.toString());
        pageBean p = stuService.listSearchClass(Class);
        return Result.success(p);
    }
    //模糊查询课程
    @PostMapping("/SelectedCourseSearch")
    public Result SelectedCourseSearch(@RequestBody Class Class) {
        log.info(Class.toString());
        pageBean p = stuService.ListSelectedCourseSearch(Class);
        return Result.success(p);
    }
    //选课
    @GetMapping("/ToSelect")
    public Result ToSelect(@RequestParam String cid, @RequestParam  String sid) {
        String message=stuService.Toselect(cid,sid);
        if(message==null){
            return Result.success();
        }else {
            return Result.error(message);
        }
    }
    //退课

    @GetMapping("/deleteClass")
    public Result deleteClass(@RequestParam String cid, @RequestParam  String sid) {
        int count=stuService.deleteClass(cid,sid);
        if(count==1){
            return Result.success();
        }else {
            return Result.error("退课失败");
        }
    }
    //查询个人信息
    @PostMapping("/StudentInfo")
    public Result ToSelect(@RequestBody Student Stu) {
        log.info(String.valueOf(Stu));
        Student stuInfo = stuService.SearchInfo(Stu.getSid());
        return Result.success(stuInfo);
    }
}
