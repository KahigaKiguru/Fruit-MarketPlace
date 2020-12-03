package com.mohanalavala.FruitMarketplaceApplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.mohanalavala.FruitMarketplaceApplication.service.BuyerService;

@Configuration
@Order(1)
public class BuyerSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	BuyerService buyerService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(buyerService).passwordEncoder(passwordEncoder);
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/buyer/**")
                .authorizeRequests()
                .antMatchers("/buyer/**", "/seller/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/buyer/loginPage")
                .defaultSuccessUrl("/buyer/buyerPage")
                .permitAll(true)
                .and()
                .logout()
                .logoutUrl("/buyer/logout")
                .logoutSuccessUrl("/buyer/loginPage")//our new logout success url, we are not replacing other defaults.
                .permitAll();//allow all as it will be accessed when user is not logged in anymore
        http.csrf().disable();
	
	}

}
