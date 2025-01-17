package com.example.demo.Controll.teacher;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.fastjson.JSON;
import com.example.demo.Listener.GeneralListener;
import com.example.demo.Service.Teacher.impl.teaService1;
import com.example.demo.pojo.*;
import com.example.demo.pojo.Class;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/teacher/class")
public class TeacherCourseControll {
    @Autowired
    private teaService1 teaService;

    //查询审核中的课程
    @GetMapping("/UnderReview")
    public Result UnderReview(@RequestParam Integer page, @RequestParam Integer pageSize,@RequestParam String tid) {
        pageBean p =teaService.listUnderReviewCourse(page,pageSize,tid);
        log.info(p.toString());
        return Result.success(p);
    }
    //查询审核未通过的课程
    @GetMapping("/UnApproved")
    public Result UnApproved(@RequestParam Integer page, @RequestParam Integer pageSize,@RequestParam String tid) {
        pageBean p =teaService.listUnApprovedCourse(page,pageSize,tid);
        log.info(p.toString());
        return Result.success(p);
    }
    //查询审核已通过的课程
    @GetMapping("/Approved")
    public Result Approved(@RequestParam Integer page, @RequestParam Integer pageSize,@RequestParam String tid) {
        pageBean p =teaService.listApprovedCourse(page,pageSize,tid);
        log.info(p.toString());
        return Result.success(p);
    }
    //申请新增课程
    @PostMapping("/increaseCourse")
    public Result IncreaseCourse(@RequestBody Class course) {
        log.info(course.toString());
        String msg=teaService.increaseCourse(course);
        if(msg==null){
            return Result.success();
        }else {
            return Result.error(msg);
        }
    }
    //取消申请
    @PostMapping("/CancelIncrease")
    public Result CancelIncrease(@RequestBody Class course) {
        log.info(String.valueOf(course.getTcid()));
        String msg=teaService.CancelIncrease(course);
        if(msg==null){
            return Result.success();
        }else {
            return Result.error(msg);
        }
    }
    //申请删除课程
    @PostMapping("/deleteCourse")
    public Result deleteCourse(@RequestBody Class course) {
        log.info(123+String.valueOf(course.getCid()));
        String msg=teaService.deleteCourse(course);
        if(msg==null){
            return Result.success();
        }else {
            return Result.error(msg);
        }
    }
    //课表查询
    @PostMapping("/timeTable")
    public Result timeTable(@RequestBody Teacher teacher){
        log.info(teacher.getTid());
        List<Class> c =teaService.timeTable(teacher.getTid());
        return Result.success(c);
    }
    //查找所有的楼
    @PostMapping("/selectBuild")
    public Result selectBuildRoom(){
        List<Build> b =teaService.selectBuildRoom();
        return Result.success(b);
    }
    //查询楼的房间什么时候空闲
    @PostMapping("/VacanrRoom")
    public Result VacanrRoom(@RequestBody Build build){
        //获取查询方式
        log.info(String.valueOf(build.getType()));
        //获得当前是周几
        LocalDateTime day=build.getDate();
        day=day.plusHours(8);
        DayOfWeek dayOfWeek = day.getDayOfWeek();
        int week=dayOfWeek.getValue();
        log.info(day.toString());
        log.info(String.valueOf(week));
// 查询该楼的所有房间
        List<Build> rooms = teaService.selectBuildRooms(build.getBname());

        List<Build> occupiedRooms = null;
// 再查询哪些房间的哪些时间是非空闲的
        if(build.getType()==1){
           occupiedRooms = teaService.selectNotVacanrRoom(build.getBname());
        }
        else if(build.getType()==0){
             occupiedRooms = teaService.selectNotVacanrRoom0(build.getBname());
        }

// 有几节课
        String[] jc = {"one", "two", "three", "four", "five"};

// 遍历所有房间
        for (Build room : rooms) {
            // 遍历非空闲房间
            for (Build occupiedRoom : occupiedRooms) {
                // 如果是同一个房间
                if (occupiedRoom.getBRoom().equals(room.getBRoom())) {
                    String time = occupiedRoom.getCTime();
                    // 周几
                    int x = Integer.parseInt(String.valueOf(time.charAt(0)));
                    // 第几节课
                    int period = Integer.parseInt(String.valueOf(time.charAt(1)));
                    if (week == x) {
                        switch (period) {
                            case 1:
                                room.setOne(1);
                                break;
                            case 2:
                                room.setTwo(1);
                                break;
                            case 3:
                                room.setThree(1);
                                break;
                            case 4:
                                room.setFour(1);
                                break;
                            case 5:
                                room.setFive(1);
                                break;
                        }
                    }
                }
            }
        }
        log.info(rooms.toString());
        return Result.success(rooms);
    }
    //录入成绩
    @PostMapping("/importCourseScore")
    public Result importCourseScore(@ModelAttribute MultipartFile file) throws Exception{
        log.info(file.getOriginalFilename());
//        3.1、建立一个通用的读取监听器，在该监听器读取到数据，并使用List保存，读取完成之后，使用getList()方法获得
        GeneralListener<Score> generalListener = new GeneralListener<>();
//        3.2、执行Excel的读取，.read()方法需要传入三个参数：1、文件。2、实体类。3、监听器。
        try{
            EasyExcel.read(file.getInputStream(),Score.class,generalListener).sheet().doRead();}
        catch (ExcelDataConvertException e){
            return Result.error("成绩格式异常 有 非数字 字符");
        }

        // 如果有错误消息，返回错误信息
        List<String> errorMessages = generalListener.getErrorMessages();
        if (!errorMessages.isEmpty()) {
            return Result.error("成绩大小超过限制:"+String.join(", ", errorMessages));
        }
//        3.3、获得监听器读取的数据列表
        List<Score> ScoreList = generalListener.getList();
//        3.4、执行后续入库操作
        log.info(ScoreList.toString());
        String msg=teaService.importCourseScore(ScoreList);

        if(msg==null){
            return Result.success();
        }
        else
            return Result.error(msg);
    }

    //导出excel成绩单
    @PostMapping("/exportCourseScore")
    public void exportCourse(HttpServletResponse response, @RequestBody Class course) throws IOException {
        // 从服务中获取要导出的数据列表
        log.info(course.toString());
        List<Score> list=teaService.exportCourseScore(course.getCid());
        List<Score> exportList = JSON.parseArray(JSON.toJSONString(list), Score.class);
        // 设置响应的内容类型和字符编码
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");

        // 文件名设置
        String filePrefix = course.getCid()+"号课程"+"导出成绩单";
        try {
            // 防止中文乱码
            String fileName = URLEncoder.encode(filePrefix, "UTF-8").replaceAll("\\+", "%20");
            // 设置Content-Disposition头，指示浏览器下载文件
            response.addHeader("Access-Control-Expose-Headers", "Content-disposition");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

            // 使用 EasyExcel 写入数据到响应流中
            EasyExcel.write(response.getOutputStream(), Score.class).sheet("模板").doWrite(exportList);
        } catch (Exception e) {
            // 处理异常并重置响应
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(JSON.toJSONString(Result.error("下载文件失败: " + e.getMessage())));
        }
        //return Result.success();
    }
}
