package com.spring.security.serviceimpl;

import com.spring.security.domain.AuthUser;
import com.spring.security.domain.UserRole;
import com.spring.security.repository.UserRepository;
import com.spring.security.repository.UserRoleRepository;
import com.spring.security.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AuthUserServiceImpl implements AuthUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthUser registerUser(AuthUser authUser) {
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        Optional<UserRole> userRole = userRoleRepository.findById(1l);
        if (userRole.isPresent()) {
            authUser.setRole(userRole.get());
        }
        return userRepository.save(authUser);
    }
}