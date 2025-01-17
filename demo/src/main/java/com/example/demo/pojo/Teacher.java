package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private String tid=null;
    private String tsex=null;
    private String tname=null;
    private LocalDate tbirth=null;
    private String tpwd=null;
    private MultipartFile image=null;
    private String tphoto=null;
    private String tcol=null;
    private Integer page=null;
    private Integer pageSize=null;
}
