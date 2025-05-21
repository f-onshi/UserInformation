package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    // データベースからパスワードを取得する
    public String getPassword(int userId) {
        // 取得した結果をリストに格納
        List<Map<String, Object>> select_result = jdbcTemplate.queryForList("SELECT password FROM users WHERE id=?", userId);
        // 取得したデータが空でない場合の処理
        if (!select_result.isEmpty()) {
            return select_result.get(0).get("password").toString(); // 最初の要素から"password"カラムの値を取得してString型に変換
        }
        return null;
    }
}