package com.example.demo.repository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserDto;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.ArrayList;


@Repository
public class SearchRepository {
    
    private final JdbcTemplate jdbcTemplate;

    public SearchRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserDto> searchUser(UserDto form) {
        
        String id = form.getId();
        String mail = form.getMail();
        String nickname = form.getNickName();
        String password = form.getPassword();
        String prefecture = form.getPrefecture();
        String tel = form.getTel();
        String remarks = form.getRemarks();
        String deleteFlag = form.getDeleteFrag();

        // StringBuilderでプレースホルダーを含んだ文字列を連結させていく
        StringBuilder sql = new StringBuilder("SELECT * FROM userInformation WHERE 1=1");

        // プレースホルダに格納する値を配列リストにまとめる
        List<Object> params = new ArrayList<>();

        // 値がnullでないかつ空でないなら...
        if (id != null && !id.isBlank()) {
            sql.append(" AND id LIKE ?");
            params.add("%" + id + "%");
        }
        if (mail != null && !mail.isEmpty()) {
            sql.append(" AND mail LIKE ?");
            params.add("%" + mail + "%");
        }
        if (nickname != null && !nickname.isEmpty()) {
            sql.append(" AND nickName LIKE ?");
            params.add("%" + nickname + "%");
        }
        if (password != null && !password.isEmpty()) {
            sql.append(" AND password LIKE ?");
            params.add("%" + password + "%");
        }
        if (prefecture != null && !prefecture.isEmpty()) {
            sql.append(" AND prefecture LIKE ?");
            params.add("%" + prefecture + "%");
        }
        if (tel != null && !tel.isEmpty()) {
            sql.append(" AND tel LIKE ?");
            params.add("%" + tel + "%");
        }
        if (remarks != null && !remarks.isEmpty()) {
            sql.append(" AND remarks LIKE ?");
            params.add("%" + remarks + "%");
        }
        if (deleteFlag != null && !deleteFlag.isEmpty()) {
            sql.append(" AND deleteFrag LIKE ?");
            params.add("%" + deleteFlag + "%");
        }

        return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(UserDto.class),params.toArray());

    }
    
}