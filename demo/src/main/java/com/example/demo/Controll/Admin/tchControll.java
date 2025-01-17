package com.example.demo.Controll.Admin;

import com.example.demo.Service.Admin.tService;
import com.example.demo.pojo.Result;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.Teacher;
import com.example.demo.pojo.pageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/admin/teacher")
//@CrossOrigin(origins = "http://localhost:7000")
public class tchControll {
    @Autowired
    private tService tservice;

    //查询所有学生到表格中
    @GetMapping
    public Result SearchAll(@RequestParam Integer page, @RequestParam Integer pageSize) {
        pageBean p = tservice.list(page, pageSize);

        return Result.success(p);
    }

    //模糊查询
    @PostMapping("/search")
    public Result Search(@RequestBody Teacher tea) {
        log.info(tea.toString());
        if(tea.getTcol()!=null&& tea.getTcol().isEmpty())
            tea.setTcol(null);
        pageBean p = tservice.listSearch(tea);
        return Result.success(p);
    }

    //查询特定学生的信息
    @PostMapping("/searchByid")
    public Result searchBysid(@RequestBody Teacher tea) {
        log.info(tea.toString());
        Teacher teacher = tservice.searchByid(tea.getTid());
        return Result.success(teacher);
    }

    //删除特定学生的信息
    @PostMapping("/deleteByid")
    public Result deleteByid(@RequestBody Teacher Tea) {
        log.info(Tea.toString());
        int count = tservice.deleteByid(Tea.getTid());
        if (count == 1)
            return Result.success();
        else
            return Result.error("失败");
    }

    //上传特定学生的信息
    @PostMapping("/update")
    public Result updateBysid(@ModelAttribute Teacher teadata) throws IOException {
        try {
            Integer.parseInt(teadata.getTcol());
        } catch (NumberFormatException e) {
            teadata.setTcol(null);
        }
        log.info(teadata.toString());
        int count = tservice.updateTeaByid(teadata);

        if (count == 1)
            return Result.success();
        else
            return Result.error("出错了");
    }
}
