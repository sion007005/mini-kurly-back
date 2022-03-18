package com.sion.minikurlyback.config;

import com.sion.minikurlyback.jwt.JwtAuthenticationEntryPoint;
import com.sion.minikurlyback.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http
        .httpBasic().disable()
        .cors()
        .configurationSource(corsConfigurationSource())
        .and()
        .csrf().disable()
        .exceptionHandling()
        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        .and()
        .authorizeRequests()
        .antMatchers( "/join").permitAll() // 회원가입과 로그인 api는 토큰이 없는 상태에서 요청이 오므로 permitAll 설정
        .antMatchers( "/login").permitAll()
        .antMatchers("/temp-password").permitAll()
        .antMatchers("/item/**", "/category/**").hasRole("ADMIN")
        .anyRequest().authenticated()
        .and()
        .apply(new JwtSecurityConfig(tokenProvider));  // JwtFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용

//        http.logout().logoutUrl("/logout").permitAll()
//                .deleteCookies("JSESSIONID")
//                .deleteCookies(headerName)
//                .logoutSuccessUrl("/")
//                .invalidateHttpSession(true);
//

    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // CORS 허용 적용
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
