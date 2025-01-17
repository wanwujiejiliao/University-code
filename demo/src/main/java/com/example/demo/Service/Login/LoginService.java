package com.example.demo.Service.Login;

import com.example.demo.pojo.LoginRole;

public interface LoginService {
    public int login(LoginRole loginRole);

    int modify(LoginRole role);
}
