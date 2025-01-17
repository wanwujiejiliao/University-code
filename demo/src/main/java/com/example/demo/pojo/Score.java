package com.example.demo.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {

    @ExcelProperty("课程id")
    private String cid;
    @ExcelProperty("课程名称")
    private String cname;
    @ExcelProperty("学生id")
    private String sid;
    @ExcelProperty("学生姓名")
    private String sname;
    @ExcelProperty("成绩")
    private double score;
}
