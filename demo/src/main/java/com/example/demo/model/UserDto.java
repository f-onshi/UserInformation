package com.example.demo.model;


import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.validation.constraints.NotEmpty;


@Entity
@Data
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @NotEmpty(message = "idを入力してください")
    private String id;

    @NotEmpty(message = "メールアドレスを入力してください")
    private String mail;

    @NotEmpty(message = "ニックネームを入力してください")
    private String nickName;

    @NotEmpty(message = "パスワードを入力してください")
    private String password;

    @NotEmpty(message =  "県名を入力してください")
    private String prefecture;

    @NotEmpty(message = "電話番号を入力してください")
    private String tel;

    //空でも大丈夫
    private String remarks;

    //空で大丈夫
    private String deleteFrag;
}
