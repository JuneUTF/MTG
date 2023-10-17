package com.juneutf.mtg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
		//CustomerUserDetailsService　読み込む（データチェック送りデータを読み込）
		@Bean
		public CustomerUserDetailsService userDetails() { 
		    return new CustomerUserDetailsService(); 
		}
		@Bean
		//PasswordEncoder　変更クラス作成
		public PasswordEncoder passwordEncoder() { 
		    return new BCryptPasswordEncoder(); 
		}
		
		//ユーザー名とパスワードをチェックします。
		protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
		    auth.userDetailsService(userDetails()).passwordEncoder(passwordEncoder()); 
		}
		@Override
		//URL　設定
		 protected void configure(final HttpSecurity http) throws Exception {
			//この脆弱性を突いた攻撃をそのままCSRF攻撃と呼びます。
            http.csrf(csrf -> csrf.disable());
			//("/", "/login", "/logout"　誰でもできます
            http.authorizeHttpRequests(requests -> requests.antMatchers("/","/style/**","/file/**","/login","/password").permitAll());
			//管理者だけはいります
            http.authorizeHttpRequests(requests -> requests.antMatchers("/kk/**").authenticated());
            http.authorizeHttpRequests().and().exceptionHandling(handling -> handling.accessDeniedPage("/about"));
			//ログイン設定
            http.authorizeHttpRequests().and()
                    .formLogin(login -> login
						//action 設定
                            .loginProcessingUrl("/login")
						//ログインページ表示設定
                            .loginPage("/login")
						//ログインOKは移動するページ設定
                            .defaultSuccessUrl("/kk")
						//ログイン失敗ページ設定
                            .failureUrl("/login?error=true")
						//ログインページの中にネームデータ取り出し
                            .usernameParameter("username")
                            .passwordParameter("password"))
                    .rememberMe(rememberMe  -> rememberMe
                    		.alwaysRemember(true)
                    		.tokenValiditySeconds(30 * 24 * 60 * 60)
                            .tokenValiditySeconds(86400)
                            .key("juneutf"))
						//ログアウトURL設定
                    .logout(logout -> logout.logoutUrl("/logout")
					//ログアウトOKのページ設定
                            .logoutSuccessUrl("/login")
                            .deleteCookies("JSESSIONID")
                    		);
		}
	}