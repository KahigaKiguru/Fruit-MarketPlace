package com.mohanalavala.FruitMarketplaceApplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.mohanalavala.FruitMarketplaceApplication.service.SellerService;

@Configuration
@Order(2)
public class SellerSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	SellerService sellerService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(sellerService).passwordEncoder(passwordEncoder);
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
		daoProvider.setUserDetailsService(sellerService);
		daoProvider.setPasswordEncoder(passwordEncoder);
		return daoProvider;
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/seller/**")
                .authorizeRequests()
                .antMatchers("/seller/**", "/buyer/**", "/css/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/seller/loginForm")
                .defaultSuccessUrl("/seller/sellerPage", true)
                .permitAll(true)
                .and()
                .logout()
                .logoutUrl("/seller/logout")
                .logoutSuccessUrl("/seller/loginForm")//our new logout success url, we are not replacing other defaults.
                .permitAll();//allow all as it will be accessed when user is not logged in anymore
    
        http.csrf().disable();
	}
	
	
	
}
