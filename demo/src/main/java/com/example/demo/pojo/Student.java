package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String sid=null;
    private String ssex=null;
    private String sname=null;
    private LocalDate sbirth=null;
    private String spwd=null;
    private String sgrade=null;
    private String sclass=null;
    private MultipartFile image=null;
    private String sphoto=null;
    private String scol=null;
    private Integer page=null;
    private Integer pageSize=null;
}
