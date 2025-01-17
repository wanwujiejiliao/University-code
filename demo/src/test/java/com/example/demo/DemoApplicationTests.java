package com.example.demo;

import com.example.demo.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private com.example.demo.Mapper.Admin.sMapper sMapper;

    @Test
    public void contextLoads() {
        Student student = new Student();
        student.setSid("2021010010001");
        sMapper.updateStuByid(student,null);

    }

}
