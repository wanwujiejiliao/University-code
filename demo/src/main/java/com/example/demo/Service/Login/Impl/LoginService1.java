package com.example.demo.Service.Login.Impl;

import com.example.demo.Mapper.Login.LMapper;
import com.example.demo.Service.Login.LoginService;
import com.example.demo.pojo.LoginRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginService1 implements LoginService {
    @Autowired
    private LMapper lMapper;
    @Override
    public int login(LoginRole loginRole) {
        String id = loginRole.getAccount();
        String password = loginRole.getPassword();
        String role = loginRole.getAuthority();
        int count=0;
        if(Objects.equals(role, "1"))
            count=lMapper.alogin(id,password);
        else if(Objects.equals(role, "2"))
            count=lMapper.slogin(id,password);
        else if(Objects.equals(role, "3"))
            count=lMapper.tlogin(id,password);
        if(count>0)
        {
            return lMapper.status();
        }
        return 0;
    }

    @Override
    public int modify(LoginRole loginRole) {
        String id = loginRole.getAccount();
        String password = loginRole.getPassword();
        String role = loginRole.getAuthority();
        if(Objects.equals(role, "1"))
            return lMapper.amodify(id,password);
        else if(Objects.equals(role, "2"))
            return lMapper.smodify(id,password);
        else if(Objects.equals(role, "3"))
            return lMapper.tmodify(id,password);
        else return 0;
    }
}
