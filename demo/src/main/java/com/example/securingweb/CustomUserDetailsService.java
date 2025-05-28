package com.example.securingweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String sql = "SELECT id, password, roll FROM userInformation WHERE id = ?"; 
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            String id = rs.getString("id");
            String password = rs.getString("password");
            String roll = rs.getString("roll");

            return User.builder()
                    .username(id)
                    .password(password)
                    .roles(roll)
                    .build();
        }, username);
    }
    




}

/*
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        // データベースからユーザー情報を取得
        // 自分の場合userInformationデータベースから取得する
        // idを基にレコードid,passwordを取得
        String sql = "SELECT id, password FROM userInformation WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> { // rsにはuser01,ksdjfadsofha
            String hashedPassword = rs.getString("password");// passwordを取得

            return org.springframework.security.core.userdetails.User.builder()
                .username(rs.getString("id"))
                .password(hashedPassword) // ハッシュ化されたパスワードを使用
                .roles("USER")// USER or ADMINでロールを構築
                .build();
        }, id); // loadUserByUsername(String id)の引数
    }
}
*/