package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Build {
    private LocalDateTime date;
    private Integer type;
    private String bid;
    private String cid;
    private Integer status;
    private String bname;
    private String bRoom;
    private Integer one=0;
    private Integer two=0;
    private Integer three=0;
    private Integer four=0;
    private Integer five=0;
    private String cTime;
}
