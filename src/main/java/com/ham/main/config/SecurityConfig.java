package com.ham.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
//		암호화시켜주는 패스워드인코더
	}
	
	
	
	
	@Bean
	//API처럼 직접 어노테이션을 줄 수 없는 경우, Bean으로 객체 생성 가능
    //스프링 풀에 객체 등록
	WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web
				.ignoring()
				.antMatchers("/css/**")
				.antMatchers("/js/**")
				.antMatchers("/vendor/**")
				.antMatchers("/img/**")
				; 
//		메서드 체이닝
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		    .cors()
		    .and()
		    .csrf()
		    .disable()
		    .authorizeHttpRequests()
				.antMatchers("/notice/add").hasRole("ADMIN") // ROLE_ADMIN에서 ROLE_제외
				.antMatchers("/manager/*").hasAnyRole("MANAGER", "ADMIN")
		    //.antMatchers("/manager/*")
				.antMatchers("/").permitAll()
				.and()
		    //form 관련 설정
			.formLogin()
			    .loginPage("/member/login")//내장된 로그인폼을 사용하지 않고, 개발자가 만든 폼을 사용
//			    POST 로그인을 처리하는 주소
			    .defaultSuccessUrl("/")
			    .failureUrl("/member/login")
		        .permitAll()
		        .and()
		    .logout()
		        .logoutUrl("/member/logout")
		        .logoutSuccessUrl("/")
		        .invalidateHttpSession(true)
		        .and()
		    .sessionManagement()
			    ;
		
		return httpSecurity.build();
	}
	
	
	
	
}