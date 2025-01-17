package com.example.demo.Controll.Admin;

import com.example.demo.Service.Admin.cService;
import com.example.demo.pojo.Class;
import com.example.demo.pojo.Result;
import com.example.demo.pojo.Teacher;
import com.example.demo.pojo.pageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin/class")
public class classControll {
    @Autowired
    private cService cService;
    //获取选课状态
    @GetMapping("/getStatus")
    public Result getStatus(){
        int status=cService.getStatus();
        return Result.success(status);
    }
    //开启课程申请
    @GetMapping("/openAudit")
    public Result openAudit(){
        cService.openAudit();
        return Result.success();
    }
    //开启选课
    @GetMapping("/openSelect")
    public Result openSelect(){
        cService.openSelect();
        return Result.success();
    }
    //开启授课
    @GetMapping("/openLecture")
    public Result openLecture(){
        cService.openLecture();
        return Result.success();
    }
    //开启录入课程
    @GetMapping("/openEnter")
    public Result openEnter(){
        cService.openEnter();
        return Result.success();
    }
    //结课
    @GetMapping("/openEnd")
    public Result openEnd(){
        cService.openEnd();
        return Result.success();
    }
    //    查询全部已选课程
    @GetMapping("/class0")
    public Result SearchAll0(@RequestParam Integer page, @RequestParam Integer pageSize,@RequestParam Integer type){
        log.info(String.valueOf(type));
        pageBean p =cService.list(page,pageSize,type);
        log.info(p.toString());
        return Result.success(p);
    }
//    模糊查询
    @PostMapping("/class0/search")
    public Result Search(@RequestBody Class Class) {
        log.info(Class.toString());
        pageBean p = cService.listSearch0(Class);
        return Result.success(p);
    }
//    审核通过
    @PostMapping("/class0/go")
    public Result go(@RequestBody Class Class) {
        int count=cService.updategood(Class);
        return Result.success();
    }
//    审核不通过
    @PostMapping("/class0/nogo")
    public Result nogo(@RequestBody Class Class) {
        int count=cService.updateno(Class);
        return Result.success();
    }
}
