package com.web.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(value = -1)//PetClinicConfigurationdan önce çalışması için
public class HalBrowserSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/hal/**").authorizeRequests().anyRequest().permitAll();//h2 console erişime ,zin verildi.
        http.csrf().disable();//csrf token devre dışı.kendi içinde auth var.
        http.headers().frameOptions().disable();//auth headerı ve diğer headerler devre dışı
    }
}
