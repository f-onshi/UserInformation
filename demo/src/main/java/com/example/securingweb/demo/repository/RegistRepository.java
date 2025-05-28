package com.example.securingweb.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.securingweb.demo.model.UserDto;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class RegistRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 入力値チェックを完了したオブジェクトが返ってくるのでSQL実行のみ
    public void userRegist(UserDto form) {

        StringBuilder sql = new StringBuilder("INSERT INTO userInformation VALUES( ?, ?, ?, ?, ?, ?, ?, ?)");

        jdbcTemplate.update(
            sql.toString()
            , form.getId()
            , form.getMail()
            , form.getNickName()
            , form.getPassword()
            , form.getPrefecture() 
            , form.getTel()
            , form.getRoll()
            , form.getDeleteFrag()
        );      
    }
}
