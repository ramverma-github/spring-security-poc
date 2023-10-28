package com.spring.security.repository;

import com.spring.security.domain.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AuthUser,Long> {
    public AuthUser findByUsername(String username);
}
