//package org.elib.auth.config;
//
//import lombok.SneakyThrows;
//import org.elib.common.security.component.GbPasswordEncoder;
//import org.elib.common.security.handler.MobileLoginSuccessHandler;
//import org.elib.common.security.mobile.MobileSecurityConfigurer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
///**
// * 认证相关配置
// *
// * @author Donald
// */
//@Primary
//@Order(90)
//@Configuration
//public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
//	@Override
//	@SneakyThrows
//	protected void configure(HttpSecurity http) {
//		http
//				.formLogin()
//				.loginPage("/token/login")
//				.loginProcessingUrl("/form/token")
//				.and()
//				.authorizeRequests()
//				.antMatchers(
//						"/sso/**",
//						"/token/**",
//						"/actuator/**",
//						"/code/**").permitAll()
//				.anyRequest().authenticated()
//				.and().csrf().disable()
//				.apply(mobileSecurityConfigurer());
//	}
//
//	/**
//	 * 不拦截静态资源
//	 *
//	 * @param web
//	 */
//	@Override
//	public void configure(WebSecurity web) {
//		web.ignoring().antMatchers("/css/**");
//		web.ignoring().antMatchers("/js/**");
//	}
//
//	@Bean
//	@Override
//	@SneakyThrows
//	public AuthenticationManager authenticationManagerBean() {
//		return super.authenticationManagerBean();
//	}
//
//	@Bean
//	public AuthenticationSuccessHandler mobileLoginSuccessHandler() {
//		return new MobileLoginSuccessHandler();
//	}
//
//	@Bean
//	public MobileSecurityConfigurer mobileSecurityConfigurer() {
//		return new MobileSecurityConfigurer();
//	}
//
//	/**
//	 * https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-storage-updated
//	 * Encoded password does not look like BCrypt
//	 *
//	 * @return PasswordEncoder
//	 */
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new GbPasswordEncoder();
////		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	}
//}