package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime tcTime;
    private List<ctimeData> ctimes;
    private Integer tcid;
    private String tid;
    private String tname;
    private String tdo;
    private String ado;
    private String cid;
    private String cname;
    private String cAddress;
    private String cTime;
    private Integer cCredit;
    private String cInfo;
    private Integer cSum;
    private Integer cNum;
    private String aInfo;
    private String cstatus;
    private String cGrade;
    private String cbuild;
    private String croom;
    private Integer page=null;
    private Integer pageSize=null;
    private Integer type=null;
    private String sid=null;
    private Double score;
}
