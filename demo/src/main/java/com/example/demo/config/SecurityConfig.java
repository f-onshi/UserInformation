
package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

 // パスワードエンコーダーの定義
 @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

 // セキュリティ設定：すべてのリクエストを許可し、ログイン画面を無効化
 @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests(auth -> auth
        .anyRequest().permitAll() // ← ここで permitAll() を使う
        )
        .csrf(csrf -> csrf.disable()) // CSRF保護を無効化（開発用）
        .formLogin(form -> form.disable()); // ログインフォームを無効化

        return http.build();
    }
}
