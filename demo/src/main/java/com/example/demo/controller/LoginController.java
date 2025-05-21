package com.example.demo.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.LoginRequest;
import com.example.demo.service.LoginService;
import com.example.demo.service.PasswordService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private PasswordService passwordService;
    // ログイン画面
    @GetMapping("/")
    public String display(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login_index";
    }

    // ログイン処理
    @PostMapping("/controller/login")
    public String login(@Validated @ModelAttribute LoginRequest loginRequest, BindingResult result, Model model) {
        // 入力エラーがある場合、登録画面にエラーメッセージを表示する
        if (result.hasErrors()) {
            List<String> errorList = new ArrayList<>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "login_index";
        }

        // 正常処理
        // データベースから入力されたIDに対応するパスワードを取得する
        String db_password = loginService.getPassword(loginRequest.getId());
        // パスワード検証処理
        if (passwordService.checkPassword(loginRequest.getPassword(), db_password)) {
            System.out.println("ログイン成功");
            return "login_success";
        } else {
            System.out.println("ログイン失敗");
            model.addAttribute("loginError", "IDまたはパスワードが正しくありません");
            return "login_index";
        }
    }
}