package com.example.securingweb;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*
 * ＠Configurationは設定クラス
 * Filterを作成するクラスとなっている
 */

@Configuration
@EnableWebSecurity // Spring Securityが有効になる
public class WebSecurityConfig {

    @Bean // SecurityFilterChainをBeanとして登録することで、リクエスト認証やアクセス制御ができる
    public SecurityFilterChain filterChain(HttpSecurity http, CustomLoginSuccessHandler successHandler) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // csrfを無効にする（開発時のみ）
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login").permitAll() // login.htmlは誰でもアクセス可能
                .requestMatchers("/indexAdmin").hasRole("ADMIN") // indexAdmin.htmlはRoleがADMINのみ
                .requestMatchers("/index").hasRole("USER") // index.htmlはRoleがUSERUSERのみ
                .anyRequest().authenticated() // 全てのエンドポイントにアクセスするには、ユーザー認証を完了させるようにする
            )

            .formLogin(form -> form
                .loginPage("/login") // ログイン認証するページを指定、書かなければSpringSecurityデフォルトのログイン機能が作動
                .successHandler(successHandler) // ログインが成功したら、successHandlerを実行する
                .permitAll()
            )

            .logout(logout -> logout.permitAll());
           
        return http.build(); // 設定済みのSecurityFilterChainを作成する
    }         

}
