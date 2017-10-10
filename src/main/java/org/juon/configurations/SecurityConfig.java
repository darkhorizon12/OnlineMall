/**
 * 
 */
package org.juon.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	public static final String REMEMBER_ME_KEY = "arahansaKey";
	@Autowired DataSource dataSource;
	@Autowired UserDetailsService userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() 
			.antMatchers("/members/new", "/home", "/download/**").permitAll()
			.antMatchers("/items/new", "/admin/**").hasRole("ADMIN")
			.antMatchers("/members/**", "/orders/**", "/items/**", "/carts/**").hasRole("USER")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/")
			.failureUrl("/loginError")	// Url to forword when failed Security Login 
			.usernameParameter("email")
			.passwordParameter("password")
			.permitAll()
			.and()
			.logout()
			.deleteCookies("remember-me")
			.logoutSuccessUrl("/")
			.permitAll()
			.and()
			.rememberMe()
			.rememberMeParameter("remember-me")
			.key(REMEMBER_ME_KEY)
			.rememberMeServices(persistentTokenBasedRememberMeServices());

	}
	
	// TOKEN BASED 방식에서 쿠키가 생성이 안됨. 다른 방법 확인 필요.
	@Bean
	public TokenBasedRememberMeServices tokenBasedRememberMeServices() {
		TokenBasedRememberMeServices tokenBasedRememberMeServices =
				new TokenBasedRememberMeServices(REMEMBER_ME_KEY, userDetailsService);
		tokenBasedRememberMeServices.setCookieName("arahansaCookie");
		
		return tokenBasedRememberMeServices;
	}
	
	// PERSISTENT BASED 방식 설정 시
	@Bean
	public PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices() {
		PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices = 
				new PersistentTokenBasedRememberMeServices(REMEMBER_ME_KEY, userDetailsService, jdbcTokenRepositoryImpl());
		
		return persistentTokenBasedRememberMeServices;
	}
	
	@Bean
	JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl() {
		JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl =
				new JdbcTokenRepositoryImpl();
		jdbcTokenRepositoryImpl.setCreateTableOnStartup(false);
		jdbcTokenRepositoryImpl.setDataSource(dataSource);
		
		return jdbcTokenRepositoryImpl;
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
