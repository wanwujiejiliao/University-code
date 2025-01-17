package com.example.demo.Controll.Admin;

import com.example.demo.Service.Admin.impl.sService1;
import com.example.demo.pojo.Result;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.pageBean;
import com.example.demo.utools.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin/student")
//@CrossOrigin(origins = "http://localhost:7000")
public class stuControll {
    @Autowired
    private sService1 sService;

    //查询所有学生到表格中
    @GetMapping
    public Result SearchAll(@RequestParam Integer page, @RequestParam Integer pageSize){
        pageBean p =sService.list(page,pageSize);

        return Result.success(p);
    }
    //模糊查询
    @PostMapping("/search")
    public Result Search(@RequestBody Student stu){
        if(stu.getScol()!=null&& stu.getScol().isEmpty())
            stu.setScol(null);
        pageBean p =sService.listSearch(stu);
        return Result.success(p);
    }

    //查询特定学生的信息
    @PostMapping("/searchByid")
    public Result searchBysid(@RequestBody Student Stu){
        log.info(Stu.toString());
        Student student=sService.searchByid(Stu.getSid());
        return Result.success(student);
    }
    //删除特定学生的信息
    @PostMapping("/deleteByid")
    public Result deleteByid(@RequestBody Student Stu){
        log.info(Stu.toString());
        int count= sService.deleteByid(Stu.getSid());
        if(count==1)
            return Result.success();
        else
            return Result.error("失败");
    }
    //上传特定学生的信息
    @PostMapping("/update")
    public Result updateBysid(@ModelAttribute Student studata) throws IOException {
        try {
            Integer.parseInt(studata.getScol());
        } catch (NumberFormatException e) {
            studata.setScol(null);
        }
        try {
            Integer.parseInt(studata.getSclass());
        } catch (NumberFormatException e) {
            studata.setSclass(null);
        }
        log.info(studata.toString());
        int count=sService.updateStuByid(studata);

        if(count==1)
            return Result.success();
        else
            return Result.error("出错了");
    }
    //学生登陆验证
//    @PostMapping("/login")
//    public Result login(@RequestParam Student stu){
//        log.info("学生登陆{}",stu);
//        Student student= sService.login(stu);
//        if(student!=null){
//            Map<String, Object> claims=new HashMap<>();
//            claims.put("sid",stu.getSid());
//            claims.put("spwd",stu.getSpwd());
//
//            String jwt = JwtUtils.generateJwt(claims);
//            return Result.success(jwt);
//        }
//        else{
//            return Result.error("用户名或者密码错误");
//        }
//    }
}
