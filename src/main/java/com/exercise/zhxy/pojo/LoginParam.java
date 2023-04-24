package com.exercise.zhxy.pojo;

import lombok.Data;

@Data
public class LoginParam {
    private String username;
    private String password;
    private String verifiCode;
    private Integer userType;
}
