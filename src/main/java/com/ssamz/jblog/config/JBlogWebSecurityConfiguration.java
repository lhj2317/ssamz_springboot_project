package com.ssamz.jblog.config;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ssamz.jblog.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class JBlogWebSecurityConfiguration {
	@Autowired
	private UserDetailsServiceImpl userDetailsService; 
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 생성자 주입
    public JBlogWebSecurityConfiguration(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 // CSRF 보호 비활성화
        http.csrf().disable();

        // 인증 경로 설정
        http.authorizeRequests()
            .requestMatchers("/WEB-INF/**","/", "/auth/**", "/js/**", "/image/**", "/webjars/**").permitAll() // 인증 없이 접근할 경로
            .anyRequest().authenticated(); // 그 외의 모든 요청은 인증이 필요함

        // 로그인 설정
        http.formLogin((formLogin) -> formLogin
				.loginPage("/auth/login")
				.loginProcessingUrl("/auth/securitylogin")  // 스프링의 시큐리티 인증 url이다. 
				.usernameParameter("username")
				.passwordParameter("password")
				.permitAll()
				.defaultSuccessUrl("/", true));

        // 로그아웃 설정
        http.logout((logout) ->logout
				.logoutUrl("/auth/logout")
				.logoutSuccessUrl("/")
				.permitAll());        

        return http.build();         
    }
	
//	@Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        // AuthenticationManagerBuilder를 사용하여 userDetailsService와 passwordEncoder 설정
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder
//            .userDetailsService(userDetailsService)  // 사용자 인증을 위한 UserDetailsService 설정
//            .passwordEncoder(passwordEncoder());  // 비밀번호 인코딩 및 비교에 사용할 PasswordEncoder 설정
//        return authenticationManagerBuilder.build();
//    }
	 
}
