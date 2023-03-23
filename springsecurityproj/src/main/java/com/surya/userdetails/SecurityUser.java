package com.surya.userdetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {
    private String username;
    private String password;
    private List<? extends GrantedAuthority> grantedAuthorities;
    public SecurityUser(String usernamwe,String password,List<? extends GrantedAuthority> grantedAuthorities)
    {
        this.username=usernamwe;
        this.password=password;
        this.grantedAuthorities=grantedAuthorities;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
      return  this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
