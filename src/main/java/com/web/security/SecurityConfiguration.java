package com.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.sql.DataSource;


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**/favicon.ico","/css/**","/js/**","/images/**","/webjars/**","/login.html").permitAll();//bu isteklerde authenticate yok
        http.authorizeRequests().antMatchers("/rest/**").access("hasRole('EDITOR')");//Rolü editor olan kullansın
        http.authorizeRequests().antMatchers("/actuator/**").access("hasRole('ADMIN')");//Rolü admin olan kullansın
        http.authorizeRequests().anyRequest().authenticated();//her isteği güvenli hale getirir
       // http.formLogin();//default form sayfası açar
        http.formLogin().loginPage("/login.html").loginProcessingUrl("/login")
                .failureUrl("/login.html?loginFailed=true");//login sayfası özelleştirildi.
        http.rememberMe().userDetailsService(userDetailsService);//beni hatırla özelliği eklendi.
        http.httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }
}
