package com.surya.autheticationproviders;

import com.surya.service.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JpaUserDetailsService jpaUserDetailsService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name=authentication.getName();
        String password= (String) authentication.getCredentials();
        UserDetails ud= jpaUserDetailsService.loadUserByUsername(name);
        System.out.println("entry");
        System.out.println(ud.getAuthorities());

        if(passwordEncoder.matches(password,ud.getPassword()))
        {
            return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), ud.getAuthorities());
        }
        else throw new BadCredentialsException("!Error");

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
