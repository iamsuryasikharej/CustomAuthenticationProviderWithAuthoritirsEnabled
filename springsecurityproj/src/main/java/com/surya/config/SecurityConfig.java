package com.surya.config;

import com.surya.autheticationproviders.CustomAuthenticationProvider;
import com.surya.service.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
            @Lazy
    AuthenticationProvider authenticationProvider;
    @Bean
    public PasswordEncoder getPaswordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }
    @Bean
    public UserDetailsService getUserDetailsService()
    {
        return new JpaUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/hello").hasAuthority("ADMIN")
                .and().httpBasic();
    }

    @Bean
    public AuthenticationProvider getAuthenticationProvider()
    {
        return new CustomAuthenticationProvider();
    }
}
