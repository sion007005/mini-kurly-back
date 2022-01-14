package com.sion.minikurlyback.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 회원가입 구현을 위해 기본 설정만 추가
    // TODO 추후 로그인 기능 구현시 개선
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .httpBasic().disable() 
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
