package com.spring.security.serviceimpl;

import com.spring.security.domain.AuthUser;
import com.spring.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = userRepository.findByUsername(username);
        SimpleGrantedAuthority grantedAuthority =new SimpleGrantedAuthority(authUser.getRole().getDescription());
        return new User(authUser.getUsername(),authUser.getPassword(), Collections.singleton(grantedAuthority));
    }
}
