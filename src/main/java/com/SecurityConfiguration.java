package com;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**/favicon.ico","/css/**","/js/**","/images/**","/webjars/**","/login.html").permitAll();//bu isteklerde authenticate yok
        http.authorizeRequests().anyRequest().authenticated();//her isteği güvenli hale getirir
       // http.formLogin();//default form sayfası açar
        http.formLogin().loginPage("/login.html").loginProcessingUrl("/login")
                .failureUrl("/login.html?loginFailed=true");
    }
}
