package com.example.demo.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginRequest implements Serializable{
    // ID
    @NotNull(message = "IDを入力してください")
    private Integer id;

    // パスワード
    @NotEmpty(message = "パスワードを入力してください")
    @Size(max = 100, message = "パスワードは100桁以内で入力してください")
    private String password;
}
