package com.example.securingweb.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.securingweb.demo.model.UserDto;
import com.example.securingweb.demo.service.RegistService;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;

import org.springframework.ui.Model;
import org.springframework.security.crypto.password.PasswordEncoder;

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

        /**
         * 登録時のエラーの場合の数
         * 
         * 1. [0 + 完全入力] : 登録処理を開始
         * 2. [0 + 未入力] : 入力してくださいエラー表示
         * 3. [1 + 完全入力or未入力] : 削除フラグは0を入力してください
         * 
         * 考え方
         * ・2.3をチェックする。エラー有ればerrorListに追加
         * ・errorListの有無で登録処理 or エラー文表示の条件分岐
         * 
         */

        // 大域に@Entityのmessageを配列リストに追加する
        List<String> errorList = new ArrayList<String>() ;

        // 入力値がない項目が有ればエラー文を取得
        if (result.hasErrors()) {           
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
        }              
        
        if ("1".equals(form.getDeleteFrag())){
            errorList.add("deleteFragが'1'の時は登録できません");
        }

        if (!errorList.isEmpty()) {
            model.addAttribute("validationError", errorList);
            model.addAttribute("backValue", form);
            return "index";
        }
        
        // パスワードのハッシュ化    
        String hashPass =  passwordEncoder.encode(form.getPassword());
        form.setPassword(hashPass);

        registService.registService(form);

        return "index";
            
    }    
}
