package com.example.securingweb;

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
        String sql = "SELECT id, password FROM users WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            String hashedPassword = rs.getString("password");

            return org.springframework.security.core.userdetails.User.builder()
                .username(rs.getString("id"))
                .password(hashedPassword) // ハッシュ化されたパスワードを使用
                .roles("USER")
                .build();
        }, id);
    }
}