package com.example.demo.Controll.Login;

import com.example.demo.Service.Login.LoginService;
import com.example.demo.pojo.LoginRole;
import com.example.demo.pojo.Result;
import com.example.demo.pojo.Student;
import com.example.demo.utools.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginControll {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result Login(@RequestBody LoginRole role)
    {
        int count = loginService.login(role);
        if(count>1){
            Map<String, Object> claims=new HashMap<>();
            claims.put("account",role.getAccount());
            claims.put("password",role.getPassword());
            claims.put("authority",role.getAuthority());
            String jwt = JwtUtils.generateJwt(claims);
            role.setJwt(jwt);
            role.setStatus(count);
            return Result.success(role);
        }
        else{
            return Result.error("用户名或者密码错误");
        }
    }
    @PostMapping("/modify")
    public Result modify(@RequestBody LoginRole role)
    {
        int count = loginService.modify(role);
        if(count==1){
            return Result.success();
        }
        else{
            return Result.error("用户名或者密码错误");
        }
    }
}
