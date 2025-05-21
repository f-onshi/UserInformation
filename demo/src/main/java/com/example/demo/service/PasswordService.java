package com.example.demo.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    // ユーザーパスワードを検証する
    public boolean checkPassword(String rawPassword, String hashedPassword) {
        // パスワードがnullでない、かつパスワードが一致すればtrueを返す、そうでないならfalseを返す
        return hashedPassword != null && passwordEncoder.matches(rawPassword, hashedPassword);
    }
}