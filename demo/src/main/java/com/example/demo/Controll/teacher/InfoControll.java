package com.example.demo.Controll.teacher;

import com.example.demo.Service.Teacher.impl.teaService1;
import com.example.demo.pojo.Result;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/teacher")
public class InfoControll {
    @Autowired
    private teaService1 teaService;

    //查询个人信息
    @PostMapping("/TeacherInfo")
    public Result ToSelect(@RequestBody Teacher tea) {
        log.info(String.valueOf(tea));
        Teacher teacher = teaService.SearchInfo(tea.getTid());
        return Result.success(teacher);
    }
}
