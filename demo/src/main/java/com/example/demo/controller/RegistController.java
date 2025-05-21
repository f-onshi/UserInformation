package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;

import org.springframework.ui.Model;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.demo.model.UserDto;
import com.example.demo.service.RegistService;

import java.util.List;
import java.util.ArrayList;

/**
 * 登録機能
 * 入力値チェック・パスワードハッシュ化
 * 
 * ＠Enttity : @Empty(message="")で入力値チェックとmessage追加
 * 
 * ＠Controller : 未入力無しなら -> passwordハッシュ化（BCryptPasswordEncoder();） -> serviceメソッド
 * ＠Service : repositoryメソッド呼び出し
 * ＠Repository : DB登録SQL実行
 * 
 * 改善
 * Serviceクラスで処理をメインにする
 * Controllerクラスは一つでよいかも
 * 
 */

@Controller
@RequestMapping("/")
public class RegistController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RegistService registService;

    @GetMapping("/regist")  
    public String index(@ModelAttribute @Validated UserDto form, BindingResult result ,Model model) {

        // 入力値がない項目が有ればエラー文を取得
        if (result.hasErrors()) {
            List<String> errorList = new ArrayList<String>() ;

            // エラー文を取得しリストに追加していく
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
                       
            model.addAttribute("validationError", errorList);

            // 入力値をindex.htmlに戻す
            model.addAttribute("backValue", form);

            return "index";

        } else {

            // パスワードのハッシュ化    
            String hashPass =  passwordEncoder.encode(form.getPassword());
            form.setPassword(hashPass);

            // serviceにformを渡す
            // 登録処理は戻り値がない。
            registService.registService(form);

            return "index";
        }      
    }    
}
