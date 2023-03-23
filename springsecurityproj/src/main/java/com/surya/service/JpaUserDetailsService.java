package com.surya.service;

import com.surya.entities.User;
import com.surya.repositories.UserRepositories;
import com.surya.userdetails.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.ArrayList;


public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepositories userRepositories;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepositories.findUserByUsername(username);
        ArrayList<GrantedAuthority> listOfAuthorities= new ArrayList<GrantedAuthority>();
        listOfAuthorities.add((GrantedAuthority) () -> "ADMIN");
        return new SecurityUser(user.getUsername(),user.getPassword(),listOfAuthorities);
    }
}
